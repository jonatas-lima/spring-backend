package com.jonatasferreira.demo.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jonatasferreira.demo.constants.Messages;
import com.jonatasferreira.demo.domain.Categoria;
import com.jonatasferreira.demo.domain.Cliente;
import com.jonatasferreira.demo.domain.ItemPedido;
import com.jonatasferreira.demo.domain.PagamentoBoleto;
import com.jonatasferreira.demo.domain.Pedido;
import com.jonatasferreira.demo.domain.enums.StatusPagamento;
import com.jonatasferreira.demo.repositories.ItemPedidoRepository;
import com.jonatasferreira.demo.repositories.PagamentoRepository;
import com.jonatasferreira.demo.repositories.PedidoRepository;
import com.jonatasferreira.demo.security.UserSS;
import com.jonatasferreira.demo.services.exceptions.AuthorizationException;
import com.jonatasferreira.demo.services.exceptions.ObjectNotFoundException;


@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private EmailService emailService;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = pedidoRepository.findById(id);
		return obj.orElseThrow(() -> 
			new ObjectNotFoundException(String.format(Messages.OBJECT_NOT_FOUND_D_S, id, Categoria.class.getName()))
		);
	}
	
	public Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException(Messages.ACESSO_NEGADO);
		}
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Cliente cliente = clienteService.find(user.getId());
		
		return pedidoRepository.findByCliente(cliente, pageRequest);
	}
	
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		
		obj.getPagamento().setStatus(StatusPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		
		if (obj.getPagamento() instanceof PagamentoBoleto) {
			PagamentoBoleto pgto = (PagamentoBoleto) obj.getPagamento();
			boletoService.preenchePagamentoBoleto(pgto, obj.getInstante());
		} 
			
		obj = pedidoRepository.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		
		for (ItemPedido item : obj.getItens()) {
			item.setDesconto(0.0);
			item.setProduto(produtoService.find(item.getProduto().getId()));
			item.setPreco(item.getProduto().getPreco());
			item.setPedido(obj);
		}
		
		itemPedidoRepository.saveAll(obj.getItens());
		
		emailService.sendOrderConfirmationEmail(obj);
		
		return obj;
	}
}
