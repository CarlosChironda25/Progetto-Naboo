package com.example.nabo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.objects.Update;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;


public class NabooBot  extends  TelegramLongPollingBot {
    private final String pathUtenti = "C:\\Users\\39348\\Desktop\\Progetto\\Progetto-Naboo\\src\\main\\resources\\com\\example\\nabo\\Dati.json";
    private final String pathNews = "C:\\Users\\39348\\Desktop\\Progetto\\Progetto-Naboo\\src\\main\\resources\\com\\example\\nabo\\Info-Notizie.json";
    public String usernameControl;
    public String passwordControl;
    Gson gson = new Gson();

    public Notizia news;

    List<Utente> Utenti;
    List<Notizia> notizia;
    boolean login;
    Update update;

    boolean booleanCommento = false;
    boolean booleanVoto = false;

    Comment_Vote comment_vote = new Comment_Vote();



    @Override
    public String getBotUsername() {
        return "MituNabooBot";
    }

    @Override
    public String getBotToken() {
        return "5457602202:AAEFla9Exb6dxvQgQe_iRLvD_dxs7ls7_Ow";
    }


    @Override
    public void onUpdateReceived(Update update) {
        this.update = update;

        if (update.hasMessage() && update.getMessage().hasText()) {    // if (bot riceives a message)

            String message = update.getMessage().getText();    // String = message received
            //System.out.println(message);


            if (message.startsWith("Login")) {
                try {
                    /**   MESSAGGIO DI ERRORE CON Login = Prova   **/
                    login = login(message);     //try login
                    if (login)          //if ( login is correct )
                        this.send(""" 
                                  Login succesfully!
                                  To see the news click on : \"/news\"""");
                    else
                        this.send(""" 
                                  Error with login,
                                  try again by clicking on : \"/login\"""");

                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

            }else if(booleanVoto){

                int voto = Integer.parseInt(message);

                if(voto <= 5 && voto >=1) {
                    try {
                        comment_vote.writeVote(voto, news);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    booleanVoto = false;
                    news = null;
                }
                else
                    this.send("Insert a vote\n(from 1 to 5)");
            }
            else if(booleanCommento){
                try {
                    comment_vote.writeComment(message, news, usernameControl /*update.getMessage().getChat().getFirstName()*/);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                booleanCommento = false;
                news = null;

            } else {

                switch (message) {

                    case "/start":
                        this.send("""
                                Thanks for joining in NabooNews.
                                \nTo see the news it's necessary to authenticate by entering the username and password.\n( ex. : Login = username password )\"""");
                        break;

                    case "/logout":
                        usernameControl = null;
                        passwordControl = null;
                        break;

                    case "/login":
                        this.send("Enter your username and password.\n( ex. : Login = username password )");
                        break;

                    case "/news":
                        if (login) {
                            benvenuto(update);
                            //System.out.println("QUI");
                            try {
                                news();
                            } catch (FileNotFoundException e) {
                                throw new RuntimeException(e);
                            } catch (MalformedURLException e) {
                                throw new RuntimeException(e);
                            }
                        } else
                            this.send("\uD83D\uDC49\uD83C\uDFFCBefore seeing the news, you need to login by clicking on command \"/login");
                        break;


                    default:
                        this.send("\uD83D\uDC49\uD83C\uDFFCSorry, I did not understand the request. Select the command \"/start");
                        break;

                }
            }

        } else if (update.hasCallbackQuery()) {

            Message message = update.getCallbackQuery().getMessage();
            CallbackQuery callbackQuery = update.getCallbackQuery();
            String data = callbackQuery.getData();

            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId().toString());


            switch (data) {

                case "CallCommenta" -> {
                    returnNotizia( update.getCallbackQuery().getMessage().getText() );     //returns the news in the "news" instance variable
                    booleanCommento = true;
                    sendMessage.setText("Insert a comment ");         //send message
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }

                case "CallVota" -> {
                    returnNotizia( update.getCallbackQuery().getMessage().getText() );
                    booleanVoto = true;
                    sendMessage.setText("Insert a vote\n(from 1 to 5)");
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }

                case "CallShowComment&Vote" -> {
                    returnNotizia(update.getCallbackQuery().getMessage().getText());
                    try {
                        sendMessage.setText("Title of news : \n" + news.getTitle() + comment_vote.getInfo(news));
                        try {
                            execute(sendMessage);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    news = null;
                }



                case "CallMenu" -> { sendMessage.setText("OK");
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                case "CallNotizie" -> {sendMessage.setText("OK2");
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                case "CallHelp" -> { sendMessage.setText("OK3");
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                case "CallButton" -> { sendMessage.setText("OK4");
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }


            }

        }

    }


    private void returnNotizia(String text) {
        for (Notizia i : notizia) {
            if ((i.toString()).equals(text)) {
                news = i;
                break;
            }
        }

    }


    private void news() throws FileNotFoundException, MalformedURLException {

        JsonReader read = new JsonReader(new FileReader(pathNews));
        notizia = gson.fromJson(read, (new TypeToken<List<Notizia>>() {
        }).getType());


        String chatId = update.getMessage().getChatId().toString();
        SendMessage sendMessage = new SendMessage();

        InlineKeyboardMarkup keyboardVoteCommentMarkup = new InlineKeyboardMarkup();   //creiamo struttura buttoni per Vote & Comment
        List<List<InlineKeyboardButton>> inlineKeybVoteComment = new ArrayList<>();    //creiamo lista di righe


        List<InlineKeyboardButton> inlineKeyboardButtonsVeC = new ArrayList<>();   //creiamo una riga dove inserire i bottoni
        List<InlineKeyboardButton> secondBottonRow = new ArrayList<>();            //creiamo una seconda riga dove vedere i risultati dei commenti e voti
        InlineKeyboardButton buttonVote = new InlineKeyboardButton("vote");     //button vote
        InlineKeyboardButton buttonComment = new InlineKeyboardButton("comment");       //button commenti
        InlineKeyboardButton buttonShowComment = new InlineKeyboardButton("show comments and votes");       //button mostra commenti

        buttonVote.setCallbackData("CallVota");         // risposta al click sul bottone
        buttonComment.setCallbackData("CallCommenta");
        buttonShowComment.setCallbackData("CallShowComment&Vote");

        inlineKeyboardButtonsVeC.add(buttonVote);       //aggiungiamo i due bottoni alla riga creata
        inlineKeyboardButtonsVeC.add(buttonComment);
        secondBottonRow.add(buttonShowComment);
        inlineKeybVoteComment.add(inlineKeyboardButtonsVeC);    //aggiungiamo la riga alla lista di righe
        inlineKeybVoteComment.add(secondBottonRow);
        keyboardVoteCommentMarkup.setKeyboard(inlineKeybVoteComment);      //aggiungiamo le liste di righe alla struttura

        int contatore = 0;

        for (Notizia n : notizia) {
            if (contatore == 10 || (n == null )) {
                break;
            }

            contatore++;
            sendMessage.setText(n.toString());
            sendMessage.setChatId(chatId);
            sendMessage.setReplyMarkup(keyboardVoteCommentMarkup);  //inseriamo al messaggio delle news la struttura dei bottoni

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }

    }


    private boolean login(String messaggio) throws FileNotFoundException {

        boolean b = false;

        messaggio = messaggio.substring(8);

        String[] passUser = messaggio.trim().split(" ");
        usernameControl = passUser[0];
        passwordControl = passUser[1];
        JsonReader leggi = new JsonReader(new FileReader(pathUtenti));
        Utenti = gson.fromJson(leggi, (new TypeToken<List<Utente>>() {
        }).getType());

        for (Utente control : Utenti) {

            if (usernameControl.equals(control.getUsername()) && passwordControl.equals(control.getPassword())) {
                if (control.isAdmin()) {
                    System.out.println("GIUSTO!!! AMMINISTRATORE");
                } else {
                    System.out.println("GIUSTO!!! UTENTE");
                }
                b = true;
                break;
            } else
                System.out.println("NO!! CREDENZIALI SBAGLIATE");
        }
        return b;

    }


    public void benvenuto(Update update) {

        if (update.hasMessage()) {                  //aggiorna : ho un messaggio ? TRUE
            Message message = update.getMessage();  // inserisco nella variabile messagge il messaggio ricevuto

            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(String.valueOf(message.getChatId()));

            System.out.println("Messaggio ricevuto!");

            sendMessage.setText("Welcome " + usernameControl);

            //button();
            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> inlineButtons = new ArrayList<>();

            List<InlineKeyboardButton> firstBottonRow = new ArrayList<>();
            List<InlineKeyboardButton> secondBottonRow = new ArrayList<>();


            /** InlineButton 1 **/
            InlineKeyboardButton menuButton = new InlineKeyboardButton();   //nuovo bottone
            menuButton.setText("Menu");
            menuButton.setCallbackData("CallMenu");
            firstBottonRow.add(menuButton);             //aggiungo a riga 1

            /** InlineButton 2 **/
            InlineKeyboardButton newsBotton = new InlineKeyboardButton();
            newsBotton.setText("News");
            newsBotton.setCallbackData("CallNotizie");
            firstBottonRow.add(newsBotton);

            /** InlineButton 3 **/
            InlineKeyboardButton helpButton = new InlineKeyboardButton();
            helpButton.setText("Help");
            helpButton.setCallbackData("CallHelp");
            secondBottonRow.add(helpButton);

            /** InlineButton 4 **/
            InlineKeyboardButton Button = new InlineKeyboardButton();   // nuovo bottone
            Button.setText("Button");
            Button.setCallbackData("CallButton");
            secondBottonRow.add(Button);            //aggiungo alla seconda riga

            inlineButtons.add(firstBottonRow);      //aggiungo bottoneRiga1
            inlineButtons.add(secondBottonRow);     //aggiungo bottoneRiga2

            inlineKeyboardMarkup.setKeyboard(inlineButtons);
            sendMessage.setReplyMarkup(inlineKeyboardMarkup);

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }

    public void send(String msg) {
        SendMessage send = new SendMessage();
        send.setChatId(this.update.getMessage().getChatId().toString());
        send.setText(msg);

        try {
            execute(send);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}

    /*public class justRoflClass {

        public justRoflClass(long chatID) {
            sendRequest(chatID);
    }*/


