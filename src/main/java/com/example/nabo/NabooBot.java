package com.example.nabo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.objects.Update;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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

    public Notizia not;

    List<Utente> Utenti;
    List<Notizia> notizia;
    boolean login;
    Update update;

    boolean commento = false;

    public Voti_Commenti votiecommenti;


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

        if (update.hasMessage() && update.getMessage().hasText()) {

            String message = update.getMessage().getText();
            //System.out.println(message);


            if (message.startsWith("Login")) {

                try {
                    login = login(message);
                    if (login)
                        this.send("LOGIN EFFETTUATO CON SUCCESSO! PER VEDERE LE NOTIZIE CLICCA : \"/news\"");
                    else
                        this.send("ERRORE COL LOGIN, RIPROVARE CLICCANDO : \"/login\" ");

                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

            } else if(commento){



                /*JsonReader read = null;
                try {
                    read = new JsonReader(new FileReader("C:\\Users\\39348\\Desktop\\Progetto\\Progetto-Naboo\\src\\main\\resources\\com\\example\\nabo\\Info-Notizie.json"));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("QQ");

                notizia = gson.fromJson(read, (new TypeToken<List<Notizia>>() {
                }).getType());

                FileWriter scriviNews = null;
                try {
                    scriviNews = new FileWriter("C:\\Users\\39348\\Desktop\\Progetto\\Progetto-Naboo\\src\\main\\resources\\com\\example\\nabo\\Info-Notizie.json");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Gson gson4 = new GsonBuilder().setPrettyPrinting().create();

                int contatore = 0;
                System.out.println("FOR");
                for (Notizia n : notizia) {
                    contatore++;
                    if(n.getTitle().equals(null) || contatore == 15)
                        break;
                    //System.out.println(n);
                    if((n.toString()).equals(not.toString())) {
                        String jsonString = gson4.toJson(n + "\n\nComment : " + message);
                        System.out.println(jsonString.toString());
                        System.out.println("..");
                        try {
                            scriviNews.write(jsonString+ ",");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        String jsonString = gson4.toJson(n);
                        System.out.println(jsonString.toString());
                        System.out.println("..");
                        try {
                            scriviNews.write(jsonString + ",");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }

                }

                try {
                    scriviNews.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }*/
                //this.send(message + "\n" + not);
                /*System.out.println("A");
                try {
                    System.out.println("AB");
                    votiecommenti.setCommento(message,not);
                    System.out.println("AABB");
                } catch (IOException e) {
                    System.out.println("CATC");
                    throw new RuntimeException(e);
                }
                System.out.println("AA");*/
                commento = false;
                not = null;
                System.out.println("BB");
                this.send(String.valueOf(not));
            } else {

                switch (message) {

                    case "/start":
                        this.send("""
                                Grazie per essere entrato in NabooNews.
                                Clicca su "/login"  e poi Inserisce la password e l'username separati da uno spazio.
                                (ex. : Login = username password)""");
                        break;

                    case "/login":
                        this.send("Inserisci username e password\n(ex. : Login = username password)");
                        break;

                    case "/news":
                        if (login) {
                            benvenuto(update);
                            System.out.println("QUI");
                            try {
                                news();
                            } catch (FileNotFoundException e) {
                                throw new RuntimeException(e);
                            } catch (MalformedURLException e) {
                                throw new RuntimeException(e);
                            }
                        } else
                            this.send("\uD83D\uDC49\uD83C\uDFFCEffettuare il login. Selezionare il comando /start ");
                        break;


                    default:
                        this.send("\uD83D\uDC49\uD83C\uDFFCScusa, non ho capito la richiesta. Selezionare il comando /start ");
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
                case "CallCommenta" -> addComment(update.getCallbackQuery().getMessage().getText(), update );

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
                    case "CallVota" -> { sendMessage.setText("Voto");
                        try {
                            execute(sendMessage);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }

            }

        }

    }


    private void addComment(String text, Update update) {

        Message message = update.getCallbackQuery().getMessage();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());

        for (Notizia value : notizia) {

            if ((value.toString()).equals(text)) {
                //System.out.println("IF");
                sendMessage.setText("Insert comment : ");
                not = (Notizia) value;
                commento = true;

                try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                break;
                }
            }
        //System.out.println("FUORI");
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
        InlineKeyboardButton buttonVote = new InlineKeyboardButton("vote");     //button vote
        InlineKeyboardButton buttonComment = new InlineKeyboardButton("comment");       //button comment
        buttonVote.setCallbackData("CallVota");         // risposta al click sul bottone
        buttonComment.setCallbackData("CallCommenta");

        inlineKeyboardButtonsVeC.add(buttonVote);       //aggiungiamo i due bottoni alla riga creata
        inlineKeyboardButtonsVeC.add(buttonComment);
        inlineKeybVoteComment.add(inlineKeyboardButtonsVeC);    //aggiungiamo la riga alla lista di righe
        keyboardVoteCommentMarkup.setKeyboard(inlineKeybVoteComment);      //aggiungiamo le liste di righe alla struttura

        int contatore = 0;

        for (Notizia n : notizia) {
            if (contatore == 10 || (n == null )) {
                break;
            }
            //notizia.add(n);
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

            sendMessage.setText("Benvenuto " + update.getMessage().getChat().getFirstName());
            sendMessage.setParseMode("Markdown");

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


