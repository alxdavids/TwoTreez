package application;
	
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Vector;

import application.Main.FilePaths;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Jerusalem extends Application 
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
	boolean timesShown = false;
	boolean tf2Shown = false;
	int counter = 0;
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
			openingScene.getStylesheets().add(getClass().getResource("treez.css").toExternalForm());
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
			newScene = feb14(primaryStage, i);
			break;
		case 15:
			newScene = feb15(primaryStage);
			break;
		case 16:
			newScene = feb16(primaryStage);
			break;
		case 17:
			newScene = feb17(primaryStage, i);
			break;
		case 18:
			newScene = feb18(primaryStage);
			break;
		case 19:
			newScene = feb19(primaryStage);
			break;
		case 20:
			newScene = feb20(primaryStage);
			break;
		case 21:
			newScene = feb21(primaryStage);
			break;
		case 22:
			newScene = feb22(primaryStage, i);
			break;
		case 23:
			newScene = feb23(primaryStage);
			break;
		}
		
		if (newScene != null)
		{
			newScene.getStylesheets().add(getClass().getResource("treez.css").toExternalForm());
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

	private Scene feb19(Stage primaryStage)
	{		
		VBox vb = new VBox(15);
		vb.setPadding(new Insets(15,15,15,15));
		Text header = new Text("I'll race you...");
		Button back = new Button();
		initBackButton(back, primaryStage);
		HBox hb = new HBox(445);
		hb.getChildren().add(header);
		hb.getChildren().add(back);
		
		Text challenge = new Text("This one is slightly different...\nYou need to beat my time which is 10.21 seconds (short mode)\n\n\n(When you press start, the window may appear behind this one)");
		Text info = new Text("When you're done close the game and press the submit times button.");
		challenge.setId(CssId.QUESTION_TEXT.getCssId());
		
		vb.getChildren().add(hb);
		vb.getChildren().add(challenge);
		vb.getChildren().add(info);
		
		Button start = new Button();
		start.setText("Start!");
		start.setOnAction( (f) -> {
			try {
				Application dragFxInstance = Main.class.newInstance();
				Stage newStage = new Stage();
				dragFxInstance.start(newStage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		Button submitTime = new Button();
		submitTime.setText("Submit times...");
		submitTime.setOnAction( (e) -> {
			File file = new File("D:\\DragFx\\Leaderboard.txt");
			
			if (!file.exists())
			{
			    return;
			}
			
			try (BufferedReader br = new BufferedReader(new FileReader(file)))
			{		    
				boolean found = false;
				while (!found)
				{
					String line = br.readLine();

					int indexOfName = line.indexOf(":");
					int indexOfTime = line.indexOf(":", indexOfName+1);
					int indexOfType = line.lastIndexOf(":");
					int indexOfTypeString = line.indexOf("Type:");

					if (indexOfName > -1)
					{
						String timeString = line.substring(indexOfTime+1, indexOfTypeString-1);
						String typeString = line.substring(indexOfType+1);
						String timeTrimmed = timeString.trim();
						typeString = typeString.trim();

						if (typeString.equals("short"))
						{
							found = true;
							Text resp = new Text("Your best time was: " + timeTrimmed);
							Text resp1 = new Text();
							Double time = Double.parseDouble(timeTrimmed);
							if (time <= 10.21)
							{
								resp1.setText("I concede that you are the finer player! Damn you...");
								resp.setId(CssId.SUCCESS_TEXT.getCssId());
								resp1.setId(CssId.SUCCESS_TEXT.getCssId());
							}
							else
							{
								resp1.setText("Unluuuuuuuuuuucky. I am master and creator!");
								resp.setId(CssId.FAIL_TEXT.getCssId());
								resp1.setId(CssId.FAIL_TEXT.getCssId());
							}
							
							if (!timesShown)
							{
								vb.getChildren().add(resp);
								vb.getChildren().add(resp1);
								timesShown = true;
							}
						}	
					}
				}
			}
			catch (IOException f) 
			{
			    f.printStackTrace();
			}
		});		
		
		vb.getChildren().add(start);
		vb.getChildren().add(submitTime);
		vb.setAlignment(Pos.TOP_CENTER);
		
		Scene s = new Scene(vb, 600, 400);
		return s;
	}

	private Scene feb21(Stage primaryStage)
	{
		BorderPane bp = new BorderPane();
		bp.setPadding(new Insets(25,25,25,25));
		VBox vb = new VBox(10);
		
		HBox hb = new HBox(430);
		Text header = new Text("Churros...");
		Button back = new Button();
		initBackButton(back, primaryStage);
		hb.getChildren().add(header);
		hb.getChildren().add(back);
		
		Text question = new Text("What is the name of the place at the red marker?");
		question.setId(CssId.QUESTION_TEXT.getCssId());
		Image img = new Image(this.getClass().getResource("barCop.png").toString());
		ImageView iv = new ImageView(img);
		TextField tf = new TextField();
		Text resp = new Text();
		
		vb.getChildren().add(question);
		vb.getChildren().add(iv);
		vb.getChildren().add(tf);
		vb.getChildren().add(resp);
		vb.setAlignment(Pos.CENTER);
		
		Button submit = new Button();
		submit.setText("Submit answers...");
		submit.setOnAction( (e) -> {
			String line = tf.getText();
			if (!line.isEmpty())
			{
				if (line.equalsIgnoreCase("Ølbaren"))
				{
					resp.setText("Ja, godt gået!");
					resp.setId(CssId.SUCCESS_TEXT.getCssId());
				}
				else
				{
					resp.setText("Nope - think about bars and Ø's.");
					resp.setId(CssId.FAIL_TEXT.getCssId());
				}
			}
		});
		
		bp.setTop(hb);
		bp.setCenter(vb);
		bp.setBottom(submit);
		BorderPane.setAlignment(submit, Pos.CENTER);
		
		Scene s = new Scene(bp, 600, 550);
		return s;
	}

	private Scene feb20(Stage primaryStage)
	{		
		VBox vb = new VBox(10);
		HBox hb = new HBox(410);
		
		Text text = new Text("It's just a day...");
		text.setId(CssId.QUESTION_TEXT.getCssId());
		Button back = new Button();
		initBackButton(back, primaryStage);
		
		hb.getChildren().add(text);
		hb.getChildren().add(back);
		
		TextField tf1 = new TextField();
		Text resp1 = new Text("");
		Text hint1 = new Text("");
		
		Text text2 = new Text("");
		TextField tf2 = new TextField();
		Text resp2 = new Text("");
		Text hint2 = new Text("");
		
		Button submit = new Button();
		submit.setText("Submit answers...");
		submit.setOnAction( (e) -> {
			String line = tf1.getText();
			if (!line.isEmpty() || !tf2Shown)
			{
				if (!tf2Shown)
				{
					if (line.equalsIgnoreCase("ALL BY MYSELF"))
					{
						resp1.setText("Wooooo. Warwick Summer Party, how could you forget?");
						resp1.setId(CssId.SUCCESS_TEXT.getCssId());
						text2.setText("He's got a brand new car...");
						text2.setId(CssId.QUESTION_TEXT.getCssId());
						vb.getChildren().remove(vb.getChildren().size()-1);
						vb.getChildren().remove(vb.getChildren().size()-1); //Oh god...
						vb.getChildren().add(text2);
						vb.getChildren().add(tf2);
						vb.getChildren().add(resp2);
						vb.getChildren().add(hint2);
						vb.getChildren().add(submit);
						tf2Shown = true;
					}
					else
					{
						counter++;
						if (counter<2)
						{
							resp1.setText(":(");
						}
						else if (counter<3)
						{
							resp1.setText(":(");
							hint1.setText("Hint: think Feeder");
						}
						else
						{
							resp1.setText(":(");
							hint1.setText("Try: ALL BY MYSELF");
						}
						resp1.setId(CssId.FAIL_TEXT.getCssId());
						hint1.setId(CssId.FAIL_TEXT.getCssId());
					}
				}
				else
				{
					String line2 = tf2.getText();
					if (!line2.isEmpty())
					{
						if (line2.equalsIgnoreCase("LOOKS LIKE A JAGUAR"))
						{
							resp2.setText("Excellent work! :)");
							resp2.setId(CssId.SUCCESS_TEXT.getCssId());
							vb.getChildren().remove(vb.getChildren().size()-2);
						}
						else
						{
							resp2.setText(":(");
							hint2.setText("Try: LOOKS LIKE A JAGUAR");
							resp2.setId(CssId.FAIL_TEXT.getCssId());
							hint2.setId(CssId.FAIL_TEXT.getCssId());
						}
					}
				}
			}
		});
		
		vb.getChildren().add(hb);
		vb.getChildren().add(tf1);
		vb.getChildren().add(resp1);
		vb.getChildren().add(hint1);
		vb.getChildren().add(submit);
		
		vb.setPadding(new Insets(10,10,10,10));
		
		Scene gs = new Scene(vb, 600, 400);
		return gs;
	}

	private Scene feb22(Stage primaryStage, int i)
	{
		String q1 = "1. What theorem was Mike trying to prove when he fell over in the kitchen?";
		String q2 = "2. Whose pint glass did I break before we went out on that same night?";
		Vector<String> answers1 = new Vector<String>();
		Vector<String> answers2 = new Vector<>();
		
		String correct1 = "Pythagoras";
		String correct2 = "Oli's";
		String wrong1 = "Chinese Remainder thm";
		String wrong2 = "Tom's";
		
		answers1.add(correct1);
		answers1.add("FTA");
		answers1.add(wrong1);
		answers1.add("Fermat's last thm");
		answers2.add("Pete's");
		answers2.add(wrong2);
		answers2.add(correct2);
		answers2.add("Mike's");
		return questionScene("Circumnavigation", q1, q2, answers1, answers2, primaryStage, i,
								correct1, correct2, wrong1, wrong2);
	}

	private Scene feb18(Stage primaryStage)
	{
		VBox vb = new VBox(20);
		HBox hb = new HBox(340);
		
		BorderPane bp = new BorderPane();
		
		Image saul = new Image(this.getClass().getResource("saul.png").toString());
		ImageView iv = new ImageView(saul);
		
		Text question = new Text("Against which team did Saul Deeney score this famous goal?");
		ToggleGroup tg1 = new ToggleGroup();
		HBox hb1 = radioButtonAnswers("Germany", "World XI", "Borussia Dortmund", "Bayern Munich", tg1);
		Text response = new Text();
		
		Text header = new Text("You're so 2010...");
		Button back = new Button();
		initBackButton(back, primaryStage);
		hb.getChildren().add(header);
		hb.getChildren().add(back);
		
		Button submit = new Button();
		submit.setText("Submit answers...");
		submit.setOnAction( (e) -> {
			RadioButton rb1 = (RadioButton)tg1.getSelectedToggle();
			if (rb1 != null)
			{
				errorShown = false;
				String answer = rb1.getText();
				if (answer.equals("Bayern Munich"))
				{
					response.setText("Correct. He went on to inspire a famous comeback from 3-1 down against England.");
					response.setId(CssId.SUCCESS_TEXT.getCssId());
				}
				else
				{
					response.setText("Noooooo! You can't have forgotten such a majestic performance??");
					response.setId(CssId.FAIL_TEXT.getCssId());
				}
			}
			else if (!errorShown)
			{
				response.setText("You haven't selected an answer...");
				response.setId(CssId.FAIL_TEXT.getCssId());
				errorShown = true;
			}
		});
		
		bp.setTop(hb);
		vb.getChildren().add(iv);
		vb.getChildren().add(question);
		vb.getChildren().add(hb1);
		vb.getChildren().add(response);
		vb.setAlignment(Pos.CENTER);
		bp.setCenter(vb);
		bp.setBottom(submit);
		BorderPane.setAlignment(submit, Pos.CENTER);
		bp.setPadding(new Insets(15,15,15,15));
		
		Scene gs = new Scene(bp, 530, 550);
		return gs;
	}

	private Scene feb17(Stage primaryStage, int i)
	{
		String q1 = "1. Where did we first see Derby win away in the league?";
		String q2 = "2. What is this cool game?";
		Vector<String> answers1 = new Vector<String>();
		Vector<String> answers2 = new Vector<>();
		
		String correct1 = "Peterborough";
		String correct2 = "A holepunch";
		String wrong1 = "Doncaster";
		String wrong2 = "A stapler";
		
		answers1.add(correct1);
		answers1.add(wrong1);
		answers1.add("Sheffield United");
		answers1.add("Barnsley");
		answers2.add("A set square");
		answers2.add(correct2);
		answers2.add("A sharpener");
		answers2.add(wrong2);
		return questionScene("A long time coming", q1, q2, answers1, answers2, primaryStage, i,
								correct1, correct2, wrong1, wrong2);
	}

	private Scene feb16(Stage primaryStage)
	{
		BorderPane bp = new BorderPane();
		BorderPane center = new BorderPane();
		bp.setPadding(new Insets(5,5,5,5));
		center.setPadding(new Insets(5,5,5,5));
		
		Text text = new Text("I thought that the questions may be getting pretty intense, "
							+ "so today is just a picture :)");
		text.setId(CssId.QUESTION_TEXT.getCssId());
		Button back = new Button();
		initBackButton(back, primaryStage);
		HBox hBoxTop = new HBox(50);
		hBoxTop.getChildren().add(text);
		hBoxTop.getChildren().add(back);
				
		Image picture = new Image(this.getClass().getResource("picture1.png").toString());
		ImageView iv = new ImageView(picture);
		center.setCenter(iv);
		
		bp.setTop(hBoxTop);
		bp.setCenter(center);
		
		Scene scene = new Scene(bp, 720, 450);
		return scene;
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
		
		String s = "\n\n\nI'll pick a penny from your ear,\nAn endless laugh, the longest tear,\n"
				+ "Will never make up for that first time,\nI made your heart beat faster than mine,\n"
				+ "So now I want you to take the fall,\nMake my heart beat slower than them all,\n"
				+ "And I'll go back to that perfect scene,\nForever and ever, evergreen,\n"
				+ "When I picked a penny from your ear,...\n";
		Text poem = new Text(s);
		TextField line = new TextField();
		Text question = new Text("What is the last line??");
		question.setId(CssId.INFO_TEXT.getCssId());
		
		Text result = new Text();
		result.setId(CssId.SUCCESS_TEXT.getCssId());
		
		Button submit = new Button();
		submit.setText("Submit answers...");
		submit.setOnAction( (e) -> {
			String answer = line.getText();
			if (!errorShown && !answer.isEmpty())
			{
				if (answer.equalsIgnoreCase(FEB_15_POEM_LAST_LINE))
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

	private Scene feb14(Stage primaryStage, int i)
	{
		String q1 = "1. Where did we first meet?";
		String q2 = "2. Where were we when I forgot to put the petrol cap back on?";
		Vector<String> answers1 = new Vector<String>();
		Vector<String> answers2 = new Vector<>();
		
		String correct1 = "Pride Park";
		String correct2 = "Bath";
		String wrong1 = "Carrow Road";
		String wrong2 = "Frome";
		
		answers1.add("Westfield Derby");
		answers1.add(correct1);
		answers1.add(wrong1);
		answers1.add("Molineux");
		answers2.add(wrong2);
		answers2.add("Nottingham");
		answers2.add("Somewhere in Wales");
		answers2.add(correct2);
		return questionScene("This one's easy...", q1, q2, answers1, answers2, primaryStage, i,
								correct1, correct2, wrong1, wrong2);
	}
	
	private Scene questionScene(String header, String question1String, String question2String,
								Vector<String> answers1, Vector<String> answers2, Stage primaryStage, 
								int i, String correct1, String correct2, String wrong1, String wrong2)
	{
		BorderPane bp = new BorderPane();
		VBox vb = new VBox(10);
		Text text = new Text(header);
		
		Button back = new Button();
		initBackButton(back, primaryStage);		
		HBox hbTop = new HBox(420);
		hbTop.getChildren().add(text);
		hbTop.getChildren().add(back);
		
		Text question1 = new Text(question1String);
		Text question2 = new Text(question2String);
		question1.setId(CssId.QUESTION_TEXT.getCssId());
		question2.setId(CssId.QUESTION_TEXT.getCssId());
		
		ToggleGroup tg1 = new ToggleGroup();
		ToggleGroup tg2 = new ToggleGroup();
		HBox hb1 = radioButtonAnswers(answers1.elementAt(0), answers1.elementAt(1), answers1.elementAt(2), answers1.elementAt(3), tg1);
		HBox hb2 = radioButtonAnswers(answers2.elementAt(0), answers2.elementAt(1), answers2.elementAt(2), answers2.elementAt(3), tg2);
		
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
				showAnswers(tg1, tg2, result1Text, result2Text, i, correct1,
							wrong1, correct2, wrong2);
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
			tf2Shown = false;
			timesShown = false;
			counter = 0;
		});
	}

	private void showAnswers(ToggleGroup tg1, ToggleGroup tg2,
			Text result1Text, Text result2Text, int i, String correct1, 
			String wrong1, String correct2, String wrong2) 
	{
		RadioButton rb1 = (RadioButton)tg1.getSelectedToggle();
		RadioButton rb2 = (RadioButton)tg2.getSelectedToggle();
		String s1 = rb1.getText();
		String s2 = rb2.getText();
		
		String s1Response = "";
		String s2Response = "";
		boolean success1 = false;
		boolean success2 = false;
		
		if (i == 14)
		{
			if (s1.equals(correct1))
			{
				s1Response = "You beat my level 1 trap of trying to get you to say Carrow Road haha, nice.";
				success1 = true;
			}
			else if (s1.equals(wrong1))
				s1Response = "I knew you'd fall for this one! We didn't meet, you just *happened* to see me.";
			else 
				s1Response = "Are you mad?";

			if (s2.equals(wrong2)) 
				s2Response = "We were on our way back from here but I'm afraid it's wrong.";
			else if (s2.equals(correct2)) {
				s2Response = "Yessssssssss. Of course it was (it took me a while to remember).";
				success2 = true;
			}
			else
				s2Response = "Nope. You are quite largely mistaken";
		}
		else if (i == 17)
		{
			if (s1.equals(correct1))
			{
				s1Response = "Yessss, you should have a look through the fixtures when Clough was manager. So many defeats...";
				success1 = true;
			}
			else if (s1.equals(wrong1))
				s1Response = "Nope, I'm afraid this was in 2014 - double denial.";
			else 
				s1Response = "Still no, I really hope you didn't choose one of these.";

			if (s2.equals(wrong2)) 
				s2Response = "It has a similar function but this isn't as cool as the right answer...";
			else if (s2.equals(correct2)) {
				s2Response = "Of course, the coolest of all cool games";
				success2 = true;
			}
			else
				s2Response = "These aren't cool (and probably not games either for that matter).";
		}
		else if (i == 22)
		{
			if (s1.equals(correct1))
			{
				s1Response = "Yep, I'm guessing you picked this one as it was the only one you knew!";
				success1 = true;
			}
			else if (s1.equals(wrong1))
				s1Response = "Noooo, it is good but Mike does't possess the knowledge to prove this...";
			else 
				s1Response = "Not this one - you should know better!";

			if (s2.equals(wrong2)) 
				s2Response = "Tom didn't go out that night due to exams.";
			else if (s2.equals(correct2)) {
				s2Response = "Yes it was Oli's - it took me nearl two years to replace it...";
				success2 = true;
			}
			else
				s2Response = "Nope, I probably wouldn't have heard it the end of it had I broken one of theirs.";
		}
		
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
