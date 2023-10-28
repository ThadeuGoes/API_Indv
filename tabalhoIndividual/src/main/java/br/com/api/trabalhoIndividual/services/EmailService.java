package br.com.api.trabalhoIndividual.services;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.com.api.trabalhoIndividual.dto.PessoaRequisicaoDTO;
import br.com.api.trabalhoIndividual.entities.Pessoa;

@Configuration
@Service
public class EmailService {

	@Autowired
	PessoaService usuarioService;
	@Autowired
	CarroService produtoService;

	private JavaMailSender emailSender;

	@Autowired
	public void setJavaMailSender(JavaMailSender emailSender) {
		this.emailSender = emailSender;
	}

//	@Autowired
//	Pedido pedido;

	@Value("${spring.mail.host}")
	private String host;

	@Value("${spring.mail.port}")
	private Integer port;

	@Value("${spring.mail.username}")
	private String username;

	@Value("${spring.mail.password}")
	private String password;

	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl emailSender = new JavaMailSenderImpl();
		Properties prop = new Properties();
		emailSender.setHost(host);
		emailSender.setPort(port);
		emailSender.setUsername(username);
		emailSender.setPassword(password);
		prop.put("mail.transport.protocol", "smtp");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		emailSender.setJavaMailProperties(prop);
		return emailSender;
	}

	public void envioEmailCadastro(PessoaRequisicaoDTO objetousuario) {
		MimeMessage mensagemCadastro = emailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(mensagemCadastro, true);
			helper.setFrom("gp4api.serratec@gmail.com");
			helper.setTo(objetousuario.getEmail());
			String nome = objetousuario.getNome();
			helper.setSubject("Olá " + nome + " sua conta foi criada com sucesso.");

			StringBuilder builder = new StringBuilder();
			builder.append("<html>\r\n");
			builder.append("<body>\r\n");
			builder.append("");
			builder.append("<div align=\"center\">\r\n");
			builder.append("<h1>Conta criada com sucesso</h1>\r\n");
			builder.append("</div>\r\n");
			builder.append("");
			builder.append("<div align=\"center\">\r\n");
			builder.append("<img src=\"cid:logo\">");
			builder.append("</div>\r\n");
			builder.append("");
			builder.append("<div align=\"center\">\r\n");
			builder.append("<p>Parabéns " + nome + " por agora fazer parte do melhor marketplace do mundo!!!</p>");
			builder.append("<p>Esperamos que tenha uma boa experiência conosco.</p>");
			builder.append(
					"<a href=http:\"//localhost:8080/api/swagger-ui/index.html#/\"\"\">Clique aqui para voltar ao site </a>\r\n");
			builder.append("<p>Atenciosamente Grupo 4.\r\n</p>");
			builder.append("");
			builder.append("</div>\r\n");
			builder.append("</body>\r\n");
			builder.append("</html>\r\n");

			helper.setText(builder.toString(), true);

			ClassPathResource imageResource = new ClassPathResource("img/image.png");
			helper.addInline("logo", imageResource);

			emailSender.send(mensagemCadastro);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void envioEmailRecuperacaoConta(Pessoa pessoa) {
		MimeMessage mensagemCadastro = emailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(mensagemCadastro, true);
			helper.setFrom("gp4api.serratec@gmail.com");
			helper.setTo(pessoa.getEmail());
			String nome = pessoa.getNome();
			helper.setSubject("Olá " + nome + " vamos recuperar o sua conta");
			StringBuilder builder = new StringBuilder();
			builder.append("<html>\r\n" + "<body>\r\n" + "" + "<div align=\"center\">\r\n"
					+ "<h1>Recuperação de conta</h1>\r\n" + "</div>\r\n" + "<br/>\r\n" + ""
					+ "<div align=\"center\">\r\n" + "<img src=\"cid:logo\">" + "</div>\r\n" + ""
					+ "<div align=\"center\">\r\n"
					+ "<p>Se vocé estáá tentando recuperar sua conta, <a href=\"http://localhost:8080/api/swagger-ui/index.html#/\">clique aqui</a> para ver o e-mail cadastrado.</p>"
					+ "<p>Se vocé não reconhece essa requisição ignore esse email.</p>"
					+ "<p>Atenciosamente Grupo 4.</p>" + "</div>" + "</body>\r\n" + "</html>\r\n");

			helper.setText(builder.toString(), true);

			ClassPathResource imageResource = new ClassPathResource("img/image.png");
			helper.addInline("logo", imageResource);

			emailSender.send(mensagemCadastro);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void envioEmailRecuperacaoSenha(Pessoa pessoa) {
		MimeMessage mensagemCadastro = emailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(mensagemCadastro, true);
			helper.setFrom("gp4api.serratec@gmail.com");
			helper.setTo(pessoa.getEmail());
			String nome = pessoa.getNome();
			helper.setSubject("Olá " + nome + " vamos recuperar a sua senha");
			StringBuilder builder = new StringBuilder();
			builder.append("<html>\r\n" + "<body>\r\n" + "" + "<div align=\"center\">\r\n"
					+ "<h1>Recuperação de senha</h1>\r\n" + "</div>\r\n" + "<br/>\r\n" + ""
					+ "<div align=\"center\">\r\n" + "<img src=\"cid:logo\">" + "</div>\r\n" + ""
					+ "<div align=\"center\">\r\n"
					+ "<p>Se vocé pediu a redefinição de senha<a href=\"http://localhost:8080/api/swagger-ui/index.html#/\"> clique aqui</a>.</p>"
					+ "<p>Se vocé não reconhece essa requisição ignore esse email.</p>"
					+ "<p>Atenciosamente Grupo 4.</p>" + "</div>" + "</body>\r\n" + "</html>\r\n");

			helper.setText(builder.toString(), true);

			ClassPathResource imageResource = new ClassPathResource("img/image.png");
			helper.addInline("logo", imageResource);

			emailSender.send(mensagemCadastro);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void envioEmailDelete(Pessoa pessoa) {
		MimeMessage mensagemCadastro = emailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(mensagemCadastro, true);
			helper.setFrom("gp4api.serratec@gmail.com");
			helper.setTo(pessoa.getEmail());
			String nome = pessoa.getNome();
			helper.setSubject("Olá " + nome + " sua conta foi apagada com sucesso.");

			StringBuilder builder = new StringBuilder();
			builder.append("<html>\r\n" + "<body>\r\n" + "" + "<div align=\"center\">\r\n"
					+ "<h1>Conta apagada com sucesso</h1>\r\n" + "</div>\r\n" + "<br/>\r\n" + ""
					+ "<div align=\"center\">\r\n" + "<img src=\"cid:logo\">" + "</div>\r\n" + ""
					+ "<div align=\"center\">\r\n" + ""
					+ "<p>Agradecemos por utilizar nossos serviços, sua conta foi finalizada.</p>"
					+ "<p>Esperamos que você tenha tido uma boa experiência conosco! Até a próxima ;)</p>"
					+ "<p>Atenciosamente Grupo 4.</p>" + "</div>" + "</body>\r\n" + "</html>\r\n");

			helper.setText(builder.toString(), true);

			ClassPathResource imageResource = new ClassPathResource("img/image.png");
			helper.addInline("logo", imageResource);

			emailSender.send(mensagemCadastro);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
