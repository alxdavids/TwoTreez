package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application 
{
	private static final String FEB_15_POEM_LAST_LINE = "And you didn't know how I did it.";
	public enum CssId {
		SUCCESS_TEXT("success-text"), FAIL_TEXT("fail-text"), OPENING_TEXT("opening-text"), 
		QUESTION_TEXT("question-text"),	INFO_TEXT("info_text");
		
		private String cssId;
		
		private CssId(String cssId)
		{
			this.cssId = cssId;
		}
		public String getCssId()
		{
			return cssId;
		}
	}
	
	boolean errorShown = false;
	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{
			BorderPane root = new BorderPane();
			Text text = new Text("I may not be here, but I made this to make up for my lack of presence.");
			Text terribleCss = new Text("Forgive my terrible CSS...");
			Text info = new Text("I've made a thing for each day that I'm gone.\nYou'll only be able to access each one after\nthat date so you can't cheat!");
			info.setId(CssId.INFO_TEXT.getCssId());
			text.setId(CssId.OPENING_TEXT.getCssId());
			root.setTop(text);
			
			BorderPane.setAlignment(text, Pos.CENTER);
					
			addButtons(root, primaryStage);
			root.setBottom(terribleCss);
			root.setRight(info);
			BorderPane.setAlignment(info, Pos.CENTER_RIGHT);
			
			Scene openingScene = new Scene(root,600,400);
			openingScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(openingScene);
			primaryStage.setTitle("For while I'm gone...");
			primaryStage.show();
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	private void addButtons(BorderPane root, Stage primaryStage)
	{
		GridPane buttonGrid = new GridPane();
		buttonGrid.setPadding(new Insets(5, 5, 5, 5));
		buttonGrid.setHgap(10);
		buttonGrid.setVgap(10);		
		
		for (int i=0; i<10; i++)
		{
			Button button = new Button();
			final int dateModifier = i+14;
			button.setText("" + dateModifier);
			
			//Need add this
			//Calendar cal = Calendar.getInstance();
			//int dateValue = cal.get(Calendar.DAY_OF_MONTH);
			int dateValue = 24;
			
			if (dateModifier>dateValue)
				button.setDisable(true);
			else
				button.setOnAction( (e) -> {
					performAction(dateModifier, primaryStage);
				});
			
			buttonGrid.add(button, i%3, i/3);
		}
		
		root.setCenter(buttonGrid);
		BorderPane.setAlignment(buttonGrid, Pos.CENTER);
	}
	
	private void performAction(int i, Stage primaryStage)
	{
		Scene newScene = null;
		switch(i) {
		case 14:
			newScene = feb14(primaryStage);
			break;
		case 15:
			newScene = feb15(primaryStage);
			break;
		case 16:
			newScene = feb16(primaryStage);
		case 17:
			newScene = feb17(primaryStage);
		case 18:
			newScene = feb18(primaryStage);
		case 19:
			newScene = feb19(primaryStage);
		case 20:
			newScene = feb20(primaryStage);
		case 21:
			newScene = feb21(primaryStage);
		case 22:
			newScene = feb22(primaryStage);
		case 23:
			newScene = feb23(primaryStage);
		}
		
		if (newScene != null)
		{
			newScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		}
		else
		{
			System.out.println("ERROR NULL SCENE");
			return;
		}
		
		primaryStage.setScene(newScene);
		primaryStage.show();
	}

	private Scene feb23(Stage primaryStage)
	{
		return null;
	}

	private Scene feb22(Stage primaryStage)
	{
		return null;
	}

	private Scene feb21(Stage primaryStage)
	{
		return null;
	}

	private Scene feb20(Stage primaryStage)
	{
		return null;
	}

	private Scene feb19(Stage primaryStage)
	{
		return null;
	}

	private Scene feb18(Stage primaryStage)
	{
		return null;
	}

	private Scene feb17(Stage primaryStage)
	{
		return null;
	}

	private Scene feb16(Stage primaryStage)
	{
		return null;
	}

	private Scene feb15(Stage primaryStage)
	{
		BorderPane bp = new BorderPane();
		bp.setPadding(new Insets(10,10,10,10));
		VBox vb = new VBox(10);
		
		HBox header = new HBox(390);
		Button backButton = new Button();
		initBackButton(backButton, primaryStage);
		Text text = new Text("Remember, remember...");
		header.getChildren().add(text);
		header.getChildren().add(backButton);		
		
		String s = "\n\n\nI'll pick a penny from your ear,\nAnd endless laugh, the longest tear,\n"
				+ "Will never make up for that first time,\nI made your heart beat faster than mine\n"
				+ "So now I want you to take the fall,\nMake my heart beat slower than them all,\n"
				+ "And I'll go back to that perfect scene,\nForever and ever, evergreen,\n"
				+ "When I picked a penny from your ear,...\n";
		Text poem = new Text(s);
		TextField line = new TextField();
		Text question = new Text("Whats the last line??");
		question.setId(CssId.INFO_TEXT.getCssId());
		
		Text result = new Text();
		result.setId(CssId.SUCCESS_TEXT.getCssId());
		
		Button submit = new Button();
		submit.setText("Submit answers...");
		submit.setOnAction( (e) -> {
			String answer = line.getText();
			if (!errorShown)
			{
				if (answer.equals(FEB_15_POEM_LAST_LINE))
				{
					result.setText("Congratulations, you remember a lot of stuff! Although"
							+ " I suspect you didn't get it right on\nyour first attempt...");
					result.setId(CssId.SUCCESS_TEXT.getCssId());
				}
				else
				{
					result.setText("Nope. You have to go back to first year of uni when I wrote "
								+ "this one so it was bordering on an\nunfair question haha."
								+ " Try writing: '" + FEB_15_POEM_LAST_LINE + "'");
					result.setId(CssId.FAIL_TEXT.getCssId());
				}
			}
		});
		
		vb.getChildren().add(poem);
		vb.getChildren().add(question);
		vb.getChildren().add(line);
		vb.getChildren().add(result);
		bp.setTop(header);
		bp.setCenter(vb);
		bp.setBottom(submit);
		BorderPane.setAlignment(submit, Pos.CENTER);
		
		Scene newScene = new Scene(bp,600,420);
		return newScene;
	}

	private Scene feb14(Stage primaryStage)
	{
		BorderPane bp = new BorderPane();
		VBox vb = new VBox(10);
		Text text = new Text("This one's easy...");
		
		Button back = new Button();
		initBackButton(back, primaryStage);		
		HBox hbTop = new HBox(430);
		hbTop.getChildren().add(text);
		hbTop.getChildren().add(back);
		
		Text question1 = new Text("1. Where did we first meet?");
		Text question2 = new Text("2. Where were we when I forgot to put the petrol cap back on?");
		question1.setId(CssId.QUESTION_TEXT.getCssId());
		question2.setId(CssId.QUESTION_TEXT.getCssId());
		
		ToggleGroup tg1 = new ToggleGroup();
		ToggleGroup tg2 = new ToggleGroup();
		HBox hb1 = radioButtonAnswers("Westfield Derby", "Pride Park", "Carrow Road", "Molineux", tg1);
		HBox hb2 = radioButtonAnswers("Frome", "Nottingham", "Somewhere in Wales", "Bath", tg2);
		
	    HBox result1 = new HBox();
	    HBox result2 = new HBox();
	    Text result1Text = new Text("");
    	Text result2Text = new Text("");
	    
	    result1.getChildren().add(result1Text);
	    result2.getChildren().add(result2Text);
	    
	    vb.setAlignment(Pos.TOP_CENTER);
		vb.getChildren().add(hbTop);
		vb.getChildren().add(question1);
		vb.getChildren().add(hb1);
		vb.getChildren().add(result1);
		vb.getChildren().add(question2);
		vb.getChildren().add(hb2);
		vb.getChildren().add(result2);
		bp.setCenter(vb);
		
		Button submit = new Button();
		submit.setText("Submit answers...");
		submit.setOnAction( (e) -> {
			RadioButton rb1 = (RadioButton)tg1.getSelectedToggle();
	    	RadioButton rb2 = (RadioButton)tg2.getSelectedToggle();
			if (rb1 != null && rb2 != null)
			{
				if (errorShown)
				{
					int size = vb.getChildren().size();
					vb.getChildren().remove(size-1); //Forgive me for all my sins...
					errorShown = false;
				}
				showAnswersFeb14(tg1, tg2, result1Text, result2Text);
			}
			else if (!errorShown)
			{
				Text errorText = new Text("You haven't selected one or more answers, jeeeez.");
				errorText.setId(CssId.FAIL_TEXT.getCssId());
				vb.getChildren().add(errorText);
				errorShown = true;
			}
		});
		
		bp.setBottom(submit);
		bp.setPadding(new Insets(10,10,10,10));
		BorderPane.setAlignment(submit, Pos.BOTTOM_CENTER);
		
		Scene newScene = new Scene(bp,600,400);
		return newScene;
	}

	private void initBackButton(Button back, Stage primaryStage) {
		back.setText("Back");
		back.setId("back-button");
		back.setOnAction( (e) -> {
			start(primaryStage);
		});
	}

	private void showAnswersFeb14(ToggleGroup tg1, ToggleGroup tg2,
			Text result1Text, Text result2Text) 
	{
		RadioButton rb1 = (RadioButton)tg1.getSelectedToggle();
		RadioButton rb2 = (RadioButton)tg2.getSelectedToggle();
		String s1 = rb1.getText();
		String s2 = rb2.getText();
		
		String s1Response = "";
		String s2Response = "";
		boolean success1 = false;
		boolean success2 = false;
		
		if (s1.equals("Pride Park"))
		{
			s1Response = "You beat my level 1 trap of trying to get you to say Carrow Road haha, nice.";
			success1 = true;
		}
		else if (s1.equals("Carrow Road"))
			s1Response = "I knew you'd fall for this one! We didn't meet, you just *happened* to see me.";
		else 
			s1Response = "Are you mad?";
		
		if (s2.equals("Frome")) 
			s2Response = "We were on our way back from here but I'm afraid it's wrong.";
		else if (s2.equals("Bath")) {
			s2Response = "Yessssssssss. Of course it was (it took me a while to remember).";
			success2 = true;
		}
		else
			s2Response = "Nope. You are quite largely mistaken";
		
		result1Text.setText(s1Response);
		result2Text.setText(s2Response);
		
		if (success1)
			result1Text.setId(CssId.SUCCESS_TEXT.getCssId());
		else
			result1Text.setId(CssId.FAIL_TEXT.getCssId());
		
		if (success2)
			result2Text.setId(CssId.SUCCESS_TEXT.getCssId());
		else
			result2Text.setId(CssId.FAIL_TEXT.getCssId());
	}

	private HBox radioButtonAnswers(String s1, String s2, String s3, String s4, ToggleGroup group) 
	{
		RadioButton radio1 = new RadioButton(s1);
		radio1.setToggleGroup(group);
	    RadioButton radio2 = new RadioButton(s2);
	    radio2.setToggleGroup(group);
	    RadioButton radio3 = new RadioButton(s3);
	    radio3.setToggleGroup(group);
	    RadioButton radio4 = new RadioButton(s4);
	    radio4.setToggleGroup(group);
	    
	    HBox hb1 = new HBox(10);
	    hb1.setAlignment(Pos.CENTER);
	    hb1.getChildren().add(radio1);
	    hb1.getChildren().add(radio2);
	    hb1.getChildren().add(radio3);
	    hb1.getChildren().add(radio4);
		return hb1;
	}

	public static void main(String[] args) 
	{
		launch(args);
	}
}
