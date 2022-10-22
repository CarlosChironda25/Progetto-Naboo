package com.example.nabo;

import com.google.gson.Gson;
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
import java.util.ArrayList;
import java.util.List;


public class NabooBot  extends  TelegramLongPollingBot {
    private final String pathUtenti = "C:\\Users\\39348\\Documents\\Università\\Primo Anno\\Programmazione\\Intellij\\Naboo\\src\\main\\resources\\com\\example\\nabo\\Dati.json";
    private final String pathNews = "C:\\Users\\39348\\Documents\\Università\\Primo Anno\\Programmazione\\Intellij\\Naboo\\src\\main\\resources\\com\\example\\nabo\\Info-Notizie.json";
    public String usernameControl;
    public String passwordControl;
    Gson gson = new Gson();

    List<Utente> Utenti;
    List<Notizia> notizia;
    boolean login;
    Update update;

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
            System.out.println(message);

            if (message.startsWith("Login")){

                try {
                    login = loginP(message);
                    if(login)
                        this.sendMessage("LOGIN EFFETTUATO CON SUCCESSO! PER VEDERE LE NOTIZIE CLICCA : \"/news\"");
                    else
                        this.sendMessage("ERRORE COL LOGIN, RIPROVARE CLICCANDO : \"/login\" ");

                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

            } else {

                switch (message) {

                    case "/start":
                        this.sendMessage("""
                                Grazie per essere entrato in NabooNews.
                                Clicca su "/login"  e poi Inserisce la password e l'username separati da uno spazio.
                                (ex. : Login = username password)""");
                        break;


                    case "/login":
                        this.sendMessage("Inserisci username e password\n(ex. : Login = username password)");
                        break;


                    case "/news" :
                        if (login) {
                            benvenuto(update);
                            try {
                                news(update);
                            } catch (FileNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                        } else
                            this.sendMessage("\uD83D\uDC49\uD83C\uDFFCEffettuare il login. Selezionare il comando /start ");
                        break;


                    default:
                        this.sendMessage("\uD83D\uDC49\uD83C\uDFFCScusa, non ho capito la richiesta. Selezionare il comando /start ");
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
                case "CallMenu" -> sendMessage.setText("OK");
                case "CallNotizie" -> sendMessage.setText("OK2");
                case "CallHelp" -> sendMessage.setText("OK3");
                case "CallButton" -> sendMessage.setText("OK4");
            }
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            }

        }



        private void news(Update update) throws FileNotFoundException{

            JsonReader read = new JsonReader(new FileReader(pathNews));
            notizia = gson.fromJson(read, (new TypeToken<List<Notizia>>() {
            }).getType());

            if (update.hasMessage()) {
                for (Notizia n : notizia) {
                        this.sendMessage(n.toString());
                }

            }

        }



        private boolean loginP(String messaggio) throws FileNotFoundException {

            boolean b = false;

            messaggio = messaggio.substring(8);

            String[] passUser = messaggio.trim().split(" ");
            usernameControl = passUser[0];
            passwordControl = passUser[1];

            JsonReader leggi = new JsonReader(new FileReader(pathUtenti));
            Utenti = gson.fromJson(leggi, (new TypeToken<List<Utente>>() {
            }).getType());

            for(Utente control : Utenti){

                if(usernameControl.equals(control.getUsername()) && passwordControl.equals(control.getPassword())) {
                    if(control.isAdmin()) {
                        System.out.println("GIUSTO!!! AMMINISTRATORE");
                    }else {
                        System.out.println("GIUSTO!!! UTENTE");
                    }
                    b = true;
                    break;
                }
                else
                    System.out.println("NO!! CREDENZIALI SBAGLIATE");
            }
            return b;

        }



        public void benvenuto (Update update){

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


                /*
                 * InlineButton 1
                 */

                InlineKeyboardButton menuButton = new InlineKeyboardButton();   //nuovo bottone
                menuButton.setText("Menu");
                menuButton.setCallbackData("CallMenu");
                firstBottonRow.add(menuButton);             //aggiungo a riga 1

                /*
                 * InlineButton 2
                 */
                InlineKeyboardButton newsBotton = new InlineKeyboardButton();
                newsBotton.setText("News");
                newsBotton.setCallbackData("CallNotizie");
                firstBottonRow.add(newsBotton);

                /*
                 * InlineButton 3
                 */
                InlineKeyboardButton helpButton = new InlineKeyboardButton();
                helpButton.setText("Help");
                helpButton.setCallbackData("CallHelp");
                secondBottonRow.add(helpButton);

                /*
                 * InlineButton 4
                 */
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



    public void sendMessage(String msg){
        SendMessage sendmessage = new SendMessage();
        sendmessage.setChatId(this.update.getMessage().getChatId().toString());
        sendmessage.setText(msg);

        try {
            execute(sendmessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }



    /*public class justRoflClass {

        public justRoflClass(long chatID) {
            sendRequest(chatID);
        }



        public justRoflClass(Update update) {

            final String text = update.getMessage().getText();
            final long chatID = update.getMessage().getChatId();
            final String[] formed = getUsernameAndPasswordFromMessage(text);
            if (formed == null) {
                sendSetAlert(chatID, false, null);
                return;
            } else {
                sendSetAlert(chatID, true, formed);
            }
            System.out.println(formed[0] + "e" + formed[1]);
            final String userName = formed[0];
            final String password = formed[1];
        }




        /*private void sendRequest(long chatID) {
            SendMessage request = new SendMessage();
            request.setChatId(String.valueOf(chatID));
            request.setText("Send your user_name and password in format \"USER_NAME:PASSWORD\"");
            try {
                //new IndexMain().executeAsync(request);
            } catch (Exception ignored) {

            }
        }

        private String[] getUsernameAndPasswordFromMessage(String text) {
            String[] formed = new String[2];
            String[] splited = text.split(":");
            if (splited.length < 2) {
                return null;
            }
            try {
                formed[0] = splited[0].split(" ")[splited[0].split(" ").length - 1];
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            formed[1] = splited[1];
            return formed;
        }

        private void sendSetAlert(String chatID, boolean success, String[] formed) {
            SendMessage request = new SendMessage();
            request.setChatId(chatID);
            request.setText(success ? ("your user_name is \"" + formed[0] + "\" and password \"" + formed[1] + "\";") : "Incorrect format. Please use this format - \"USER_NAME:PASSWORD\"");
            try {
                //new IndexMain().executeAsync(request);
            } catch (Exception ignored) {

            }
        }*/

    }
