package com.jonatasferreira.demo.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jonatasferreira.demo.domain.Categoria;
import com.jonatasferreira.demo.domain.Cidade;
import com.jonatasferreira.demo.domain.Cliente;
import com.jonatasferreira.demo.domain.Endereco;
import com.jonatasferreira.demo.domain.Estado;
import com.jonatasferreira.demo.domain.ItemPedido;
import com.jonatasferreira.demo.domain.Pagamento;
import com.jonatasferreira.demo.domain.PagamentoBoleto;
import com.jonatasferreira.demo.domain.PagamentoCartao;
import com.jonatasferreira.demo.domain.Pedido;
import com.jonatasferreira.demo.domain.Produto;
import com.jonatasferreira.demo.domain.enums.Perfil;
import com.jonatasferreira.demo.domain.enums.StatusPagamento;
import com.jonatasferreira.demo.domain.enums.TipoCliente;
import com.jonatasferreira.demo.repositories.CategoriaRepository;
import com.jonatasferreira.demo.repositories.CidadeRepository;
import com.jonatasferreira.demo.repositories.ClienteRepository;
import com.jonatasferreira.demo.repositories.EnderecoRepository;
import com.jonatasferreira.demo.repositories.EstadoRepository;
import com.jonatasferreira.demo.repositories.ItemPedidoRepository;
import com.jonatasferreira.demo.repositories.PagamentoRepository;
import com.jonatasferreira.demo.repositories.PedidoRepository;
import com.jonatasferreira.demo.repositories.ProdutoRepository;

@Service
public class DBService {

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

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public void instantiateTestDatabase() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Categoria c1 = new Categoria("Informática");
		Categoria c2 = new Categoria("Escritório");
		Categoria c3 = new Categoria("Cama, mesa e banho");
		Categoria c4 = new Categoria("Eletrônicos");
		Categoria c5 = new Categoria("Jardinagem");
		Categoria c6 = new Categoria("Decoração");
		Categoria c7 = new Categoria("Perfumaria");

		Produto p1 = new Produto("Computador", 2000.0);
		Produto p2 = new Produto("Impressora", 800.0);
		Produto p3 = new Produto("Mouse", 80.0);
		Produto p4 = new Produto("Mesa de escritório", 300.0);
		Produto p5 = new Produto("Toalha", 50.0);
		Produto p6 = new Produto("Colcha", 200.0);
		Produto p7 = new Produto("TV true color", 1200.0);
		Produto p8 = new Produto("Roçadeira", 800.0);
		Produto p9 = new Produto("Abajour", 100.0);
		Produto p10 = new Produto("Pendente", 180.0);
		Produto p11 = new Produto("Shampoo", 90.0);

		Estado e1 = new Estado("Minas Gerais");
		Estado e2 = new Estado("São Paulo");
		Estado e3 = new Estado("Paraíba");

		Cidade cid1 = new Cidade("Uberlândia", e1);
		Cidade cid2 = new Cidade("São Paulo", e2);
		Cidade cid3 = new Cidade("Campina Grande", e3);

		Cliente cli1 = new Cliente("Maria Silva", "jonatas.lima@ccc.ufcg.edu.br", "63394822016",
				TipoCliente.PESSOA_FISICA, passwordEncoder.encode("123"));

		Cliente cli2 = new Cliente("Ana Costa", "ana.costa@gmail.com", "16564905017", TipoCliente.PESSOA_FISICA,
				passwordEncoder.encode("1234"));
		cli2.addPerfil(Perfil.ADMIN);

		Endereco end1 = new Endereco("rua prefeito francisco camilo", "316", "mercadinho ferreira", "catole",
				"58410280", cid3, cli1);
		Endereco end2 = new Endereco("avenida matos", "105", "sala 800", "centro", "31231233", cid2, cli1);
		Endereco end3 = new Endereco("travessa francisco camilo", "110", "beco", "catole", "58410282", cid2, cli2);

		Pedido ped1 = new Pedido(sdf.parse("30/09/2017 10:32"), cli1, end1);
		Pedido ped2 = new Pedido(sdf.parse("10/10/2017 19:35"), cli1, end2);

		Pagamento pag1 = new PagamentoCartao(StatusPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pag1);

		Pagamento pag2 = new PagamentoBoleto(StatusPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pag2);

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.0);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.0);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.0);

		Produto p12 = new Produto("Produto 12", 10.00);
		Produto p13 = new Produto("Produto 13", 10.00);
		Produto p14 = new Produto("Produto 14", 10.00);
		Produto p15 = new Produto("Produto 15", 10.00);
		Produto p16 = new Produto("Produto 16", 10.00);
		Produto p17 = new Produto("Produto 17", 10.00);
		Produto p18 = new Produto("Produto 18", 10.00);
		Produto p19 = new Produto("Produto 19", 10.00);
		Produto p20 = new Produto("Produto 20", 10.00);
		Produto p21 = new Produto("Produto 21", 10.00);
		Produto p22 = new Produto("Produto 22", 10.00);
		Produto p23 = new Produto("Produto 23", 10.00);
		Produto p24 = new Produto("Produto 24", 10.00);
		Produto p25 = new Produto("Produto 25", 10.00);
		Produto p26 = new Produto("Produto 26", 10.00);
		Produto p27 = new Produto("Produto 27", 10.00);
		Produto p28 = new Produto("Produto 28", 10.00);
		Produto p29 = new Produto("Produto 29", 10.00);
		Produto p30 = new Produto("Produto 30", 10.00);
		Produto p31 = new Produto("Produto 31", 10.00);
		Produto p32 = new Produto("Produto 32", 10.00);
		Produto p33 = new Produto("Produto 33", 10.00);
		Produto p34 = new Produto("Produto 34", 10.00);
		Produto p35 = new Produto("Produto 35", 10.00);
		Produto p36 = new Produto("Produto 36", 10.00);
		Produto p37 = new Produto("Produto 37", 10.00);
		Produto p38 = new Produto("Produto 38", 10.00);
		Produto p39 = new Produto("Produto 39", 10.00);
		Produto p40 = new Produto("Produto 40", 10.00);
		Produto p41 = new Produto("Produto 41", 10.00);
		Produto p42 = new Produto("Produto 42", 10.00);
		Produto p43 = new Produto("Produto 43", 10.00);
		Produto p44 = new Produto("Produto 44", 10.00);
		Produto p45 = new Produto("Produto 45", 10.00);
		Produto p46 = new Produto("Produto 46", 10.00);
		Produto p47 = new Produto("Produto 47", 10.00);
		Produto p48 = new Produto("Produto 48", 10.00);
		Produto p49 = new Produto("Produto 49", 10.00);
		Produto p50 = new Produto("Produto 50", 10.00);
		
		c1.adicionaProdutos(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27,
						p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, p38, p39, p40, p41, p42, p43, p44, p45, p46, p47,
						p48, p49, p50));
		
		p12.getCategorias().add(c1);
		p13.getCategorias().add(c1);
		p14.getCategorias().add(c1);
		p15.getCategorias().add(c1);
		p16.getCategorias().add(c1);
		p17.getCategorias().add(c1);
		p18.getCategorias().add(c1);
		p19.getCategorias().add(c1);
		p20.getCategorias().add(c1);
		p21.getCategorias().add(c1);
		p22.getCategorias().add(c1);
		p23.getCategorias().add(c1);
		p24.getCategorias().add(c1);
		p25.getCategorias().add(c1);
		p26.getCategorias().add(c1);
		p27.getCategorias().add(c1);
		p28.getCategorias().add(c1);
		p29.getCategorias().add(c1);
		p30.getCategorias().add(c1);
		p31.getCategorias().add(c1);
		p32.getCategorias().add(c1);
		p33.getCategorias().add(c1);
		p34.getCategorias().add(c1);
		p35.getCategorias().add(c1);
		p36.getCategorias().add(c1);
		p37.getCategorias().add(c1);
		p38.getCategorias().add(c1);
		p39.getCategorias().add(c1);
		p40.getCategorias().add(c1);
		p41.getCategorias().add(c1);
		p42.getCategorias().add(c1);
		p43.getCategorias().add(c1);
		p44.getCategorias().add(c1);
		p45.getCategorias().add(c1);
		p46.getCategorias().add(c1);
		p47.getCategorias().add(c1);
		p48.getCategorias().add(c1);
		p49.getCategorias().add(c1);
		p50.getCategorias().add(c1);

		ped1.adicionaItens(Arrays.asList(ip1, ip2));
		ped2.adicionaItens(Arrays.asList(ip3));

		p1.adicionaItens(Arrays.asList(ip1));
		p2.adicionaItens(Arrays.asList(ip3));
		p3.adicionaItens(Arrays.asList(ip2));

		cli1.adicionaTelefones(new HashSet<>(Arrays.asList("987234567", "998111991")));
		cli1.adicionaEnderecos(Arrays.asList(end1, end2));
		cli1.adicionaPedidos(Arrays.asList(ped1, ped2));

		cli2.adicionaTelefones(new HashSet<>(Arrays.asList("93883321", "33337378")));
		cli2.adicionaEnderecos(end3);

		e1.adicionaCidades(Arrays.asList(cid1));
		e2.adicionaCidades(Arrays.asList(cid2));
		e3.adicionaCidades(Arrays.asList(cid3));

		c1.adicionaProdutos(Arrays.asList(p1, p2, p3));
		c2.adicionaProdutos(Arrays.asList(p2, p4));
		c3.adicionaProdutos(Arrays.asList(p5, p6));
		c4.adicionaProdutos(Arrays.asList(p1, p2, p3, p7));
		c5.adicionaProdutos(Arrays.asList(p8));
		c6.adicionaProdutos(Arrays.asList(p9, p10));
		c7.adicionaProdutos(Arrays.asList(p11));

		p1.adicionaCategorias(Arrays.asList(c1, c4));
		p2.adicionaCategorias(Arrays.asList(c1, c2, c4));
		p3.adicionaCategorias(Arrays.asList(c1, c4));
		p4.adicionaCategorias(Arrays.asList(c2));
		p5.adicionaCategorias(Arrays.asList(c3));
		p6.adicionaCategorias(Arrays.asList(c3));
		p7.adicionaCategorias(Arrays.asList(c4));
		p8.adicionaCategorias(Arrays.asList(c5));
		p9.adicionaCategorias(Arrays.asList(c6));
		p10.adicionaCategorias(Arrays.asList(c6));
		p11.adicionaCategorias(Arrays.asList(c7));

		categoriaRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20,
				p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
				p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
		estadoRepository.saveAll(Arrays.asList(e1, e2, e3));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));
		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepository.saveAll(Arrays.asList(end1, end2));
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pag1, pag2));
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}
}
