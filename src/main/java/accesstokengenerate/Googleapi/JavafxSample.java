package accesstokengenerate.Googleapi;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.controlsfx.control.CheckComboBox;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.admin.directory.DirectoryScopes;
import com.google.api.services.classroom.ClassroomScopes;
import com.google.api.services.drive.DriveScopes;

import javafx.application.Application; 
import javafx.collections.FXCollections; 
import javafx.collections.ObservableList; 
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.geometry.Insets; 
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.control.CheckBox; 
import javafx.scene.control.ChoiceBox; 
import javafx.scene.control.DatePicker; 
import javafx.scene.control.ListView; 
import javafx.scene.control.RadioButton; 
import javafx.scene.layout.GridPane; 
import javafx.scene.text.Text; 
import javafx.scene.control.TextField; 
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleButton; 
import javafx.stage.Stage; 

public class JavafxSample extends Application {
	@Override
	public void start(final Stage stage) throws Exception {
		String accesstoken = null;
		String refreshToken = null;
		final Map<String, Set<String>> scopeMap =  new HashMap<String, Set<String>>();
		Set<String> googleAdminScope = new HashSet<String>() ;
		String strClientId = "1078665368108-nqjb0v8s7dju3rkdsqskmcn7uudpd5ie.apps.googleusercontent.com";
		String strClientSecret = "7ISzkKUYFzVEHJE-Yep6I8RO";
		googleAdminScope.add(DirectoryScopes.ADMIN_DIRECTORY_USER);
		scopeMap.put("Google Classroom all Service",ClassroomScopes.all());
		scopeMap.put("Google Drive all service",DriveScopes.all());
		scopeMap.put("Google User Create/Delete Services",googleAdminScope);
		// Label for name
		Text clientId = new Text("Client Id");

		// Text field for name
		final TextField clientIdText = new TextField();
		clientIdText.setText(strClientId);

		// Label for date of birth
		Text clientSecret = new Text("Client Secret");

		
		// Text field for name
		final TextField clientSecretText = new TextField();
		clientSecretText.setText(strClientSecret);

		final Text message = new Text("Successfully Generated Access token!");
		message.setVisible(false);
		// Label for date of birth
		Text scope = new Text("Application Scope"); 
        ObservableList<String> items = FXCollections.observableArrayList();
        
        items.addAll(scopeMap.keySet());

        final CheckComboBox<String> combo_box = new CheckComboBox<String>(items);
		// Label for register
		final Button buttonGenerate = new Button("Generate");
		combo_box.setMaxSize(200, 10);
		final Button buttonCpyFullToken = new Button("Copy All Token");
		final Button buttonRestartApplication = new Button("Restart Application");
		buttonCpyFullToken.setVisible(false);
		buttonRestartApplication.setVisible(false);
		buttonCpyFullToken.setTranslateX(100);
		buttonRestartApplication.setTranslateX(80);
		// Creating a Grid Pane
		GridPane gridPane = new GridPane();

		// Setting size for the pane
		gridPane.setMinSize(400, 300);

		// Setting the padding
		gridPane.setPadding(new Insets(10, 10, 10, 10));

		// Setting the vertical and horizontal gaps between the columns
		gridPane.setVgap(5);
		gridPane.setHgap(5);

		// Setting the Grid alignment
		gridPane.setAlignment(Pos.CENTER);

		// Arranging all the nodes in the grid
		gridPane.add(clientId, 0, 0);
		gridPane.add(clientIdText, 1, 0);

		gridPane.add(clientSecret, 0, 1);
		gridPane.add(clientSecretText, 1, 1);
		
		gridPane.add(scope, 0, 2);
		gridPane.add(combo_box, 1, 2);

		gridPane.add(buttonGenerate, 1, 3);
		gridPane.add(message, 0, 4,3,1);
		gridPane.add(buttonCpyFullToken, 0, 5);
		gridPane.add(buttonRestartApplication, 0, 6);
		stage.getIcons().add(new Image(JavafxSample.class.getResourceAsStream("/dp-icon.png")));

		// Styling nodes
		buttonGenerate.setStyle("-fx-background-color: #090a0c,linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%), linear-gradient(#20262b, #191d22), radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));		    -fx-background-radius: 5,4,3,5;		    -fx-background-insets: 0,1,2,0;		    -fx-text-fill: white;		    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );		    -fx-font-family: 'Arial';		    -fx-text-fill: linear-gradient(white, #d0d0d0);		    -fx-font-size: 12px;		    -fx-padding: 10 20 10 20;");
		buttonCpyFullToken.setStyle("-fx-background-color: linear-gradient(#f2f2f2, #d6d6d6),linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%),  linear-gradient(#dddddd 0%, #f6f6f6 50%);  -fx-background-radius: 8,7,6; -fx-background-insets: 0,1,2; -fx-text-fill: black; -fx-effect: dropshadow(three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
		buttonRestartApplication.setStyle("-fx-background-color: linear-gradient(#f2f2f2, #d6d6d6),linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%),  linear-gradient(#dddddd 0%, #f6f6f6 50%);  -fx-background-radius: 8,7,6; -fx-background-insets: 0,1,2; -fx-text-fill: black; -fx-effect: dropshadow(three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
	
		buttonGenerate.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
            	NetHttpTransport HTTP_TRANSPORT = null;
				try {
					HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
				} catch (GeneralSecurityException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				List<String> SCOPES =new ArrayList<String>();
				for(String selected : combo_box.getCheckModel().getCheckedItems())
				{
					for(String scope : scopeMap.get(selected)) {
						SCOPES.add(scope);
					}
					
				}
            	 GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clientIdText.getText(), clientSecretText.getText(), SCOPES)
     	                        .setAccessType("offline").build();
            	 Credential credential = null;
            	 buttonGenerate.setVisible(false);
				try {
					credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
					buttonCpyFullToken.setId(clientIdText.getText() + "|" + clientSecretText.getText() + "|" + credential.getAccessToken()+ "|"+ credential.getRefreshToken());
					buttonCpyFullToken.setVisible(true);
					buttonRestartApplication.setVisible(true);
					message.setVisible(true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					message.setText("Error while generating the token : " + e.getLocalizedMessage());
					message.setVisible(true);
				}
								
     	       
            }
        });
		
		buttonCpyFullToken.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				final Clipboard clipboard = Clipboard.getSystemClipboard();
				final ClipboardContent content = new ClipboardContent();
				content.putString(buttonCpyFullToken.getId());
				clipboard.setContent(content);
				
			}
		
		});
		
		buttonRestartApplication.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				try {
					start(stage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		// Setting the back ground color
		gridPane.setStyle("-fx-background-color: BEIGE;");

		// Creating a scene object
		Scene scene = new Scene(gridPane);

		// Setting title to the Stage
		stage.setTitle("Access Token Generator");

		// Adding scene to the stage
		stage.setScene(scene);

		// Displaying the contents of the stage
		stage.show();
	}

	public static void main(String args[]) {
		launch(args);
	}
}
