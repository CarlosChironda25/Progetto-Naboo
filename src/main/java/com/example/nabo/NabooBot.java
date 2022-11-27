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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class NabooBot  extends  TelegramLongPollingBot {
    private final String pathUtenti = "C:\\Users\\mitug\\OneDrive\\Desktop\\Nuova cartella\\Progetto-Naboo\\src\\main\\resources\\com\\example\\nabo\\DataBase\\Dati.json";
    private final String pathNews = "C:\\Users\\mitug\\OneDrive\\Desktop\\Nuova cartella\\Progetto-Naboo\\src\\main\\resources\\com\\example\\nabo\\DataBase\\Info-Notizie.json";
    public String usernameControl;
    public String passwordControl;
    Gson gson = new Gson();

    Date tempo = new Date(System.currentTimeMillis());
    public Notizia news;

    List<Utente> Utenti;
    List<Notizia> notizia;
    boolean login;
    Update update;

    boolean booleanAuthors = false;
    boolean booleanCategories = false;
    boolean booleanCommento = false;
    boolean booleanVoto = false;

    boolean booleanTimes = false;

    boolean booleanKeyword = false;

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
                    if (login) {      //if ( login is correct )
                        this.send(""" 
                                Login succesfully!
                                To see the news click on : \"/news\"""");

                        if (login) {
                            benvenuto();
                            System.out.println("QUI");
                        } else
                            this.send("\uD83D\uDC49\uD83C\uDFFCBefore seeing the news, you need to login by clicking on command \"/login");
                    } else
                        this.send(""" 
                                Error with login,
                                try again by clicking on : \"/login\"""");

                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

            } else if (booleanCategories) {
                try {
                    filterCategory(message);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

            } else if (booleanKeyword) {
                try {
                    filterKeyword(message);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

            }else if (booleanAuthors) {
                try {
                    filterAuthors(message);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

            } else if (booleanTimes) {
                try {
                    filterTime(message);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

            } else if (booleanVoto) {

                int voto = Integer.parseInt(message);

                if (voto <= 5 && voto >= 1) {
                    try {
                        comment_vote.writeVote(voto, news);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    booleanVoto = false;
                    news = null;
                } else
                    this.send("Insert a vote\n(from 1 to 5)");

            } else if (booleanCommento) {
                try {
                    comment_vote.writeComment(message, news, usernameControl);
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
                            benvenuto();
                            //System.out.println("QUI");
                            /*try {
                                //news(s);
                            } catch (FileNotFoundException e) {
                                throw new RuntimeException(e);
                            }*/
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
                    returnNotizia(update.getCallbackQuery().getMessage().getText());     //returns the news in the "news" instance variable
                    booleanCommento = true;
                    sendMessage.setText("Insert a comment ");         //send message
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }

                case "CallVota" -> {
                    returnNotizia(update.getCallbackQuery().getMessage().getText());
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


                case "CallMenu" -> {
                    sendMessage.setText("OK");
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                case "CallNotizie" -> {
                    try {
                        news(sendMessage);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "CallHelp" -> {
                    sendMessage.setText("OK3");
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                case "CallFilter" -> filtering(sendMessage);

                case "CallCategory" -> {
                    booleanCategories = true;
                    sendMessage.setText("Inserisci una categoria\n" +
                            "(ex.: \"Economia\", \"Cultura\", \"Scienze\", ...)");
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }

                case "CallAuthors" -> {
                    booleanAuthors = true;
                    sendMessage.setText("Inserisci un autore\n" +
                            "(ex. : \"Aldo Cazzullo\", \"Ida Bozzi\", \"Antonio Carioti\", ...)");
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }

                case "CallTime" -> {
                    booleanTimes = true;
                    sendMessage.setText("Inserisci la data\n" +
                            "(ex. : \"22-11-2022\")");
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }

                case "CallKeyword" -> {
                    booleanKeyword = true;
                    sendMessage.setText("Inserisci la parola chiave");
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }


            }

        }

    }


    private void filtering(SendMessage sendMessage) {

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();   //creiamo struttura buttoni per Vote & Comment
        List<List<InlineKeyboardButton>> inlineKeyboard = new ArrayList<>();    //creiamo lista di righe


        List<InlineKeyboardButton> firstButtonRow = new ArrayList<>();   //creiamo una riga dove inserire i bottoni
        List<InlineKeyboardButton> secondButtonRow = new ArrayList<>();            //creiamo una seconda riga dove vedere i risultati dei commenti e voti
        InlineKeyboardButton buttonCategoy = new InlineKeyboardButton("Categoria");     //button vote
        InlineKeyboardButton buttonAuthor = new InlineKeyboardButton("Autore");       //button commenti
        InlineKeyboardButton buttonDate = new InlineKeyboardButton("Per data");       //button mostra commenti
        InlineKeyboardButton buttonKeyword = new InlineKeyboardButton("Parola chiave");       //button mostra commenti

        buttonCategoy.setCallbackData("CallCategory");         // risposta al click sul bottone
        buttonAuthor.setCallbackData("CallAuthors");
        buttonDate.setCallbackData("CallTime");
        buttonKeyword.setCallbackData("CallKeyword");


        firstButtonRow.add(buttonCategoy);       //aggiungiamo i due bottoni alla riga creata
        firstButtonRow.add(buttonAuthor);
        secondButtonRow.add(buttonDate);
        secondButtonRow.add(buttonKeyword);
        inlineKeyboard.add(firstButtonRow);    //aggiungiamo la riga alla lista di righe
        inlineKeyboard.add(secondButtonRow);
        keyboardMarkup.setKeyboard(inlineKeyboard);

        sendMessage.setText("Seziona il filtro");
        sendMessage.setReplyMarkup(keyboardMarkup);  //inseriamo al messaggio delle news la struttura dei bottoni

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    private void filterAuthors(String author) throws FileNotFoundException {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(this.update.getMessage().getChatId()));

        JsonReader read = new JsonReader(new FileReader(pathNews));
        notizia = gson.fromJson(read, (new TypeToken<List<Notizia>>() {
        }).getType());

        boolean trova = false;

        for (Notizia n : notizia) {
            if (n == null) {
                break;
            } else if (n.getAutore().equalsIgnoreCase(author)) {
                trova = true;
                sendMessage.setText(n.toString());

                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
        if (!trova) {
            sendMessage.setText("Autore non valido");
            //sendMessage.setReplyMarkup(keyboardVoteCommentMarkup);  //inseriamo al messaggio delle news la struttura dei bottoni
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private void filterCategory(String category) throws FileNotFoundException {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(this.update.getMessage().getChatId()));

        JsonReader read = new JsonReader(new FileReader(pathNews));
        notizia = gson.fromJson(read, (new TypeToken<List<Notizia>>() {
        }).getType());

        boolean trova = false;

        for (Notizia n : notizia) {
            if (n == null) {
                break;
            } else if (n.getCategory().equalsIgnoreCase(category)) {
                trova = true;
                sendMessage.setText(n.toString());
                //sendMessage.setReplyMarkup(keyboardVoteCommentMarkup);  //inseriamo al messaggio delle news la struttura dei bottoni

                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
        if (!trova) {
            sendMessage.setText("Categoria non valida");
            //sendMessage.setReplyMarkup(keyboardVoteCommentMarkup);  //inseriamo al messaggio delle news la struttura dei bottoni
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private void filterKeyword(String keyword) throws FileNotFoundException {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(this.update.getMessage().getChatId()));

        JsonReader read = new JsonReader(new FileReader(pathNews));
        notizia = gson.fromJson(read, (new TypeToken<List<Notizia>>() {
        }).getType());

        boolean trova = false;

        for (Notizia n : notizia) {
            if (n == null) {
                break;
            } else if (n.getTitle().contains(keyword) || n.getDescrizione().contains(keyword)) {
                trova = true;
                sendMessage.setText(n.toString());
                //sendMessage.setReplyMarkup(keyboardVoteCommentMarkup);  //inseriamo al messaggio delle news la struttura dei bottoni

                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }

        if (!trova) {
            sendMessage.setText("Parola chiave non valida");
            //sendMessage.setReplyMarkup(keyboardVoteCommentMarkup);  //inseriamo al messaggio delle news la struttura dei bottoni
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }



    private void filterTime(String time) throws FileNotFoundException {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(this.update.getMessage().getChatId()));

        JsonReader read = new JsonReader(new FileReader(pathNews));
        notizia = gson.fromJson(read, (new TypeToken<List<Notizia>>() {
        }).getType());

        Date dataNews = new Date();
        dataNews.setDate(Integer.parseInt(time.substring(0,2)));
        dataNews.setMonth(Integer.parseInt(time.substring(3,5))-1);   //i mesi partono da indice 0
        dataNews.setYear(Integer.parseInt(time.substring(6)) - 1900);    //il contro degli anni parte da 1900
        dataNews.setHours(0);
        dataNews.setMinutes(0);
        dataNews.setSeconds(0);


        boolean trova = false;

        for (Notizia n : notizia) {
            if (n == null ) {
                break;
            } else if( (n.getData().getTime() >= dataNews.getTime() ) && ( n.getData().getTime() < (dataNews.getTime() + 86400000)) ) {
                trova = true;
                sendMessage.setText(n.toString());
                //sendMessage.setReplyMarkup(keyboardVoteCommentMarkup);  //inseriamo al messaggio delle news la struttura dei bottoni

                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

        }
        if(!trova) {
            sendMessage.setText("Tempo non valido");
            //sendMessage.setReplyMarkup(keyboardVoteCommentMarkup);  //inseriamo al messaggio delle news la struttura dei bottoni
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
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


    private void news(SendMessage sendMessage) throws FileNotFoundException {

        JsonReader read = new JsonReader(new FileReader(pathNews));
        notizia = gson.fromJson(read, (new TypeToken<List<Notizia>>() {
        }).getType());


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

            if(n.getData().getDay() >= (tempo.getDay() - 2))
                System.out.println(n);


            if (contatore == 10 || (n == null )) {
                break;
            }

            contatore++;
            sendMessage.setText(n.toString());
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
               System.out.println("credenziali corrette");
                b = true;
                break;
            } else
                System.out.println("NO!! CREDENZIALI SBAGLIATE");
        }
        return b;

    }


    public void benvenuto() {

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
            newsBotton.setText("View all news");
            newsBotton.setCallbackData("CallNotizie");
            firstBottonRow.add(newsBotton);

            /** InlineButton 3 **/
            InlineKeyboardButton helpButton = new InlineKeyboardButton();
            helpButton.setText("Help");
            helpButton.setCallbackData("CallHelp");
            secondBottonRow.add(helpButton);

            /** InlineButton 4 **/
            InlineKeyboardButton Button = new InlineKeyboardButton();   // nuovo bottone
            Button.setText("Filter");
            Button.setCallbackData("CallFilter");
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

