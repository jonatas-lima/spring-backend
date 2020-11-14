package com.jonatasferreira.demo;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jonatasferreira.demo.domain.Categoria;
import com.jonatasferreira.demo.domain.Cidade;
import com.jonatasferreira.demo.domain.Cliente;
import com.jonatasferreira.demo.domain.Endereco;
import com.jonatasferreira.demo.domain.Estado;
import com.jonatasferreira.demo.domain.Pagamento;
import com.jonatasferreira.demo.domain.PagamentoBoleto;
import com.jonatasferreira.demo.domain.PagamentoCartao;
import com.jonatasferreira.demo.domain.Pedido;
import com.jonatasferreira.demo.domain.Produto;
import com.jonatasferreira.demo.domain.enums.StatusPagamento;
import com.jonatasferreira.demo.domain.enums.TipoCliente;
import com.jonatasferreira.demo.repositories.CategoriaRepository;
import com.jonatasferreira.demo.repositories.CidadeRepository;
import com.jonatasferreira.demo.repositories.ClienteRepository;
import com.jonatasferreira.demo.repositories.EnderecoRepository;
import com.jonatasferreira.demo.repositories.EstadoRepository;
import com.jonatasferreira.demo.repositories.PagamentoRepository;
import com.jonatasferreira.demo.repositories.PedidoRepository;
import com.jonatasferreira.demo.repositories.ProdutoRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Categoria c1 = new Categoria("Informática");
		Categoria c2 = new Categoria("Escritório");
		
		Produto p1 = new Produto("Computador", 2000.0);
		Produto p2 = new Produto("Impressora", 800.0);
		Produto p3 = new Produto("Mouse", 80.0);
		
		Estado e1 = new Estado("Minas Gerais"); 
		Estado e2 = new Estado("São Paulo"); 
		Estado e3 = new Estado("Paraíba"); 
		
		Cidade cid1 = new Cidade("Uberlândia", e1);
		Cidade cid2 = new Cidade("São Paulo", e2);
		Cidade cid3 = new Cidade("Campina Grande", e3);
		
		Cliente cli1 = new Cliente("Maria Silva", "maria@gmail.com", "31231231313", TipoCliente.PESSOA_FISICA);
		Endereco end1 = new Endereco("rua prefeito francisco camilo", "316", "mercadinho ferreira", "catole", "58410280", cid3, cli1);
		Endereco end2 = new Endereco("avenida matos", "105", "sala 800", "centro", "31231233", cid2, cli1);
		
		Pedido ped1 = new Pedido(sdf.parse("30/09/2017 10:32"), cli1, end1);
		Pedido ped2 = new Pedido(sdf.parse("10/10/2017 19:35"), cli1, end2);
		
		Pagamento pag1 = new PagamentoCartao(StatusPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pag1);
		
		Pagamento pag2 = new PagamentoBoleto(StatusPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pag2);
				
		cli1.adicionaTelefones(new HashSet<>(Arrays.asList("987234567", "998111991")));
		cli1.adicionaEnderecos(Arrays.asList(end1, end2));
		cli1.adicionaPedidos(Arrays.asList(ped1, ped2));
		
		e1.adicionaCidades(Arrays.asList(cid1));
		e2.adicionaCidades(Arrays.asList(cid2));
		e3.adicionaCidades(Arrays.asList(cid3));
		
		c1.adicionaProdutos(Arrays.asList(p1, p2, p3));
		c2.adicionaProdutos(Arrays.asList(p2));
		
		p1.adicionaCategorias(Arrays.asList(c1));
		p2.adicionaCategorias(Arrays.asList(c1, c2));
		p3.adicionaCategorias(Arrays.asList(c1));
		
		categoriaRepository.saveAll(Arrays.asList(c1, c2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		estadoRepository.saveAll(Arrays.asList(e1, e2, e3));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));
		clienteRepository.save(cli1);
		enderecoRepository.saveAll(Arrays.asList(end1, end2));
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pag1, pag2));
	}

}
