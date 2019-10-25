package com.ramoncosta.cursomc.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ramoncosta.cursomc.domain.Categoria;
import com.ramoncosta.cursomc.domain.Cidade;
import com.ramoncosta.cursomc.domain.Cliente;
import com.ramoncosta.cursomc.domain.Endereco;
import com.ramoncosta.cursomc.domain.Estado;
import com.ramoncosta.cursomc.domain.ItemPedido;
import com.ramoncosta.cursomc.domain.Pagamento;
import com.ramoncosta.cursomc.domain.PagamentoComBoleto;
import com.ramoncosta.cursomc.domain.PagamentoComCartao;
import com.ramoncosta.cursomc.domain.Pedido;
import com.ramoncosta.cursomc.domain.Produto;
import com.ramoncosta.cursomc.domain.enums.EstadoPagamento;
import com.ramoncosta.cursomc.domain.enums.Perfil;
import com.ramoncosta.cursomc.domain.enums.TipoCliente;
import com.ramoncosta.cursomc.repository.CategoriaRepository;
import com.ramoncosta.cursomc.repository.CidadeRepository;
import com.ramoncosta.cursomc.repository.ClienteRepository;
import com.ramoncosta.cursomc.repository.EnderecoRepository;
import com.ramoncosta.cursomc.repository.EstadoRepository;
import com.ramoncosta.cursomc.repository.ItemPedidoRepository;
import com.ramoncosta.cursomc.repository.PagamentoRepository;
import com.ramoncosta.cursomc.repository.PedidoRepository;
import com.ramoncosta.cursomc.repository.ProdutoRepository;

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
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncode;
	
	public void instantiateTestDatabase() throws ParseException {
		Categoria categoria1 = new Categoria(null, "Informatica");
		Categoria categoria2 = new Categoria(null, "Escritorio");
		Categoria categoria3 = new Categoria(null, "Cama, Mesa e Banho");
		Categoria categoria4 = new Categoria(null, "Eletrônicos");
		Categoria categoria5 = new Categoria(null, "Jardinagem");
		Categoria categoria6 = new Categoria(null, "Decoração");
		Categoria categoria7 = new Categoria(null, "Perfumaria");
		Categoria categoria8 = new Categoria(null, "Infantil");
		Categoria categoria9 = new Categoria(null, "Test");
		Categoria categoria10 = new Categoria(null, "TestNew");
		
		Produto produto1 = new Produto(null, "Computador", 2000.00);
		Produto produto2 = new Produto(null, "Impressora", 1000.00);
		Produto produto3 = new Produto(null, "Mouse", 100.00);
		Produto produto4 = new Produto(null, "Mesa de Escritório", 100.00);
		Produto produto5 = new Produto(null, "Toalha", 100.00);
		Produto produto6 = new Produto(null, "Colcha", 100.00);
		Produto produto7 = new Produto(null, "TV True Color", 100.00);
		Produto produto8 = new Produto(null, "Roçaceira", 100.00);
		Produto produto9 = new Produto(null, "Abajour", 100.00);
		Produto produto10 = new Produto(null, "Pendente", 100.00);
		Produto produto11 = new Produto(null, "Shampoo", 100.00);
		
		categoria1.getProdutos().addAll(Arrays.asList(produto1, produto2, produto3));
		categoria2.getProdutos().addAll(Arrays.asList(produto2, produto4));
		categoria3.getProdutos().addAll(Arrays.asList(produto5, produto6));
		categoria4.getProdutos().addAll(Arrays.asList(produto1, produto2, produto3, produto7));
		categoria5.getProdutos().addAll(Arrays.asList(produto8));
		categoria6.getProdutos().addAll(Arrays.asList(produto9, produto10));
		categoria7.getProdutos().addAll(Arrays.asList(produto11));
		
		produto1.getCategorias().addAll(Arrays.asList(categoria1, categoria4));
		produto2.getCategorias().addAll(Arrays.asList(categoria1,categoria2, categoria4));
		produto3.getCategorias().addAll(Arrays.asList(categoria1, categoria4));
		produto4.getCategorias().addAll(Arrays.asList(categoria2));
		produto5.getCategorias().addAll(Arrays.asList(categoria3));
		produto6.getCategorias().addAll(Arrays.asList(categoria3));
		produto7.getCategorias().addAll(Arrays.asList(categoria4));
		produto8.getCategorias().addAll(Arrays.asList(categoria5));
		produto9.getCategorias().addAll(Arrays.asList(categoria6));
		produto10.getCategorias().addAll(Arrays.asList(categoria6));
		produto11.getCategorias().addAll(Arrays.asList(categoria7));
		
		categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2, categoria3, categoria4, categoria5, categoria6, categoria7, categoria8, categoria9, categoria10));
		produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3, produto4, produto5, produto6, produto7, produto8, produto9, produto10, produto11));

		//########################################################################################
		
		Estado estado1 = new Estado(null, "Amazonas");
		Estado estado2 = new Estado(null, "São Paulo");
		Estado estado3 = new Estado(null, "Minas Gerais");		
		
		Cidade cidade1 = new Cidade(null, "Manaus", estado1);
		Cidade cidade2 = new Cidade(null, "Campinas", estado2);
		Cidade cidade3 = new Cidade(null, "Poços de Calda", estado3);
		Cidade cidade4 = new Cidade(null, "São Paulo", estado2);		
		
		estado1.getCidades().addAll(Arrays.asList(cidade1));
		estado2.getCidades().addAll(Arrays.asList(cidade2, cidade4));
		estado3.getCidades().addAll(Arrays.asList(cidade3));
		
		estadoRepository.saveAll(Arrays.asList(estado1, estado2, estado3));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3, cidade4));
		
		//########################################################################################
		
		Cliente cliente1 = new Cliente(null, "Ramon Costa", "ramon@gmail.com", "44562359005", TipoCliente.PESSOAFISICA, passwordEncode.encode("123"));
		Cliente cliente2 = new Cliente(null, "Juan Rodrigues", "juan@gmail.com", "30433968044", TipoCliente.PESSOAFISICA, passwordEncode.encode("123"));
		cliente2.addPerfil(Perfil.ADMIN);
		
		cliente1.getTelefones().addAll(Arrays.asList("92 995284817", "92 35816878")) ;
		cliente2.getTelefones().addAll(Arrays.asList("92 991474117", "92 35816878")) ;
		
		Endereco endereco1 = new Endereco(null, "Rua São Miguel", "332", "Casa com Muro Alto", "Col. Santo Antonio", "1234", cliente1, cidade1);
		Endereco endereco2 = new Endereco(null, "Rua ABC", "07", "Casa com Muro Alto", "Cidade Nova", "43121", cliente1, cidade2);
		Endereco endereco3 = new Endereco(null, "Travessa São Tomé", "04", "Casa com Muro Alto", "Col. Santo Antonio", "1234", cliente2, cidade1);
		
		cliente1.getEnderecos().addAll(Arrays.asList(endereco1, endereco2));
		cliente2.getEnderecos().addAll(Arrays.asList(endereco3));
		
		
		clienteRepository.saveAll(Arrays.asList(cliente1, cliente2));
		enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2, endereco3));
		
		//########################################################################################
		
		SimpleDateFormat simpleDateFortmat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido pedido1 = new Pedido(null, simpleDateFortmat.parse("30/09/2017 10:32"), cliente1, endereco1);
		Pedido pedido2 = new Pedido(null, simpleDateFortmat.parse("10/10/2017 19:35"), cliente1, endereco2);
		
		Pagamento pagamento1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, pedido1, 6);
		pedido1.setPagamento(pagamento1);
		
		Pagamento pagamento2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, pedido2, simpleDateFortmat.parse("20/10/2017 00:00"), null);
		pedido2.setPagamento(pagamento2);
		
		cliente1.getPedidos().addAll(Arrays.asList(pedido1, pedido2));
		
		pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2));
		pagamentoRepository.saveAll(Arrays.asList(pagamento1, pagamento2));
		
		//########################################################################################
		
		ItemPedido itemPedido1 = new ItemPedido(pedido1, produto1, 0.00, 1, 2000.00);
		ItemPedido itemPedido2 = new ItemPedido(pedido1, produto3, 0.00, 2, 80.00);
		ItemPedido itemPedido3 = new ItemPedido(pedido2, produto2, 100.00, 1, 800.00);
		
		pedido1.getItens().addAll(Arrays.asList(itemPedido1, itemPedido2));
		pedido2.getItens().addAll(Arrays.asList(itemPedido3));
		
		produto1.getItens().addAll(Arrays.asList(itemPedido1));
		produto2.getItens().addAll(Arrays.asList(itemPedido3));
		produto3.getItens().addAll(Arrays.asList(itemPedido2));
		
		itemPedidoRepository.saveAll(Arrays.asList(itemPedido1,itemPedido2, itemPedido3 ));
	}
}
