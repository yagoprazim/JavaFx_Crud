module br.com.trabalhoprofthiago.yagoprazim {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    opens br.com.trabalhoprofthiago.yagoprazim.maven to javafx.fxml;
    exports br.com.trabalhoprofthiago.yagoprazim.maven;
}