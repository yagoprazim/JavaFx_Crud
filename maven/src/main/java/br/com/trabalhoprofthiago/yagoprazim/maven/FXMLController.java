package br.com.trabalhoprofthiago.yagoprazim.maven;
/*
Put header here


 */

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLController implements Initializable {

	// IMPORTAÇÕES:
	@FXML
	private Label validNome, validN1, validN2, valid2Nome, valid2N1, valid2N2;

	@FXML
	private TextField tfNome, tfN1, tfN2;

	@FXML
	private Button gerar;

	@FXML
	private TableView<Aluno> tbGerenciar;
	// -----------------------------------------------------------------------

	// OBSERVABLE LIST:
	@FXML
	private TableColumn<Aluno, String> columnNome, columnN1, columnN2, columnMedia, columnSituacao;
	private ObservableList<Aluno> listaAlunos = FXCollections.observableArrayList(

	);
	// -----------------------------------------------------------------------

	// MÉTODO CONTENDO O RESULTADO QUE SERÁ GERADO AO CLICAR NO BOTÃO:
	@FXML
	private void adicionarAluno(ActionEvent event) {
		// Limpa a borda vermelha toda vez que clicar no botão e não tiver erros:
		tfNome.getStyleClass().remove("bordaVermelha");
		tfN1.getStyleClass().remove("bordaVermelha");
		tfN2.getStyleClass().remove("bordaVermelha");

		// Armazena os textFields recebidos em variáveis temporárias para facilitar o
		// uso nas validações:
		String nome = tfNome.getText();
		String nota1 = tfN1.getText();
		String nota2 = tfN2.getText();

		// Seta true nas labes de validação, para aparecer o texto em vermelho:
		validNome.setVisible(nome.isEmpty());
		validN1.setVisible(nota1.isEmpty());
		validN2.setVisible(nota2.isEmpty());
		valid2Nome.setVisible(!nome.matches("[a-zA-Z ]+") && !nome.isEmpty());
		valid2N1.setVisible(!nota1.matches("^(?:10(?:\\.0+)?|[0-9](?:\\.[0-9]+)?)$") && !nota1.isEmpty());
		valid2N2.setVisible(!nota2.matches("^(?:10(?:\\.0+)?|[0-9](?:\\.[0-9]+)?)$") && !nota2.isEmpty());

		// Seta true nas bordas vermelhas (foi criada uma class 'bordaVermelha' lá em style definindo a cor da borda):
		TextField[] campos = { tfNome, tfN1, tfN2 };
		Label[] validacoes = { validNome, validN1, validN2 };
		for (int i = 0; i < campos.length; i++) {
			campos[i].getStyleClass().addAll(validacoes[i].isVisible() ? "bordaVermelha" : "");
		}
		Label[] validacoes1 = { valid2Nome, valid2N1, valid2N2 };
		for (int i = 0; i < campos.length; i++) {
			campos[i].getStyleClass().addAll(validacoes1[i].isVisible() ? "bordaVermelha" : "");
		}

		// Alerta de validação para as regras gerais:
		if (nome.isEmpty() || nota1.isEmpty() || nota2.isEmpty() || !nome.matches("[a-zA-Z ]+")
				|| !nota1.matches("^(?:10(?:\\.0+)?|[0-9](?:\\.[0-9]+)?)$")
				|| !nota2.matches("^(?:10(?:\\.0+)?|[0-9](?:\\.[0-9]+)?)$")) {

			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setTitle("Erro");
			alerta.setHeaderText("Preencha todos os campos corretamente!");
			alerta.showAndWait();

		// Cadastra um aluno:
		} else {
			Aluno aluno = new Aluno(nome, nota1, nota2);
			listaAlunos.add(aluno);
			tbGerenciar.setItems(listaAlunos);

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Cadastro de aluno");
			alert.setHeaderText(null);
			alert.setContentText("Aluno cadastrado com sucesso!");
			alert.showAndWait();

			// Limpa os campos e labels de validação após o cadastro com sucesso:
			tfNome.clear();
			tfN1.clear();
			tfN2.clear();
			validNome.setVisible(false);
			validN1.setVisible(false);
			validN2.setVisible(false);
			valid2Nome.setVisible(false);
			valid2N1.setVisible(false);
			valid2N2.setVisible(false);
		}
	}
	// -----------------------------------------------------------------------

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		//Serve para mostrar o que foi capturado chamando o método get de cada propriedade da class Aluno, aparecendo em cada coluna da tableview:
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		columnN1.setCellValueFactory(new PropertyValueFactory<>("nota1"));
		columnN2.setCellValueFactory(new PropertyValueFactory<>("nota2"));
		columnMedia.setCellValueFactory(new PropertyValueFactory<>("media"));
		columnSituacao.setCellValueFactory(new PropertyValueFactory<>("situacao"));

		// Seta as labels como false assim que inicializar o programa:
		Label[] labels = { validNome, validN1, validN2, valid2Nome, valid2N1, valid2N2 };
		for (Label label : labels) {
			label.setVisible(false);
		}
	}
}
