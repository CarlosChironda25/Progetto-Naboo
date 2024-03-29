package com.example.nabo;

import com.example.nabo.GUI.UserRegistrationController;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.rometools.rome.io.FeedException;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.BotSession;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;


public class NabooBot  extends  TelegramLongPollingBot {

    public static BotSession botSession;

    private String usernameControl;
    private String passwordControl;

    private Notizia news;

    private List<Utente> Utenti;
    private List<Notizia> notizia;
    private boolean userLoggato;

    private Update update;

    private boolean booleanAuthors = false;
    private boolean booleanCategories = false;
    private boolean booleanCommento = false;
    private boolean booleanVoto = false;
    private boolean booleanTime = false;
    private boolean booleanKeyword = false;

    private Commento_Voto commento_voto = new Commento_Voto();

    public NabooBot() throws MalformedURLException {
    }


    @Override
    public String getBotUsername() {
        return "NabooProgetto_bot";
    }

    @Override
    public String getBotToken() {
        return "6197481926:AAENtN0mlQyQwY8FqDBEy6h6kf2h96NSf8c";
    }


    @Override
    public void onUpdateReceived(Update update) {
        this.update = update;

        if (update.hasMessage() && update.getMessage().hasText()) {    // if (bot riceve un messaggio)

            String message = update.getMessage().getText();    // String = message ricevuto
            System.out.println("Messaggio ricevuto  : " + message);

            if(!userLoggato) {

                if (message.startsWith("Login = ") || message.startsWith("login = ")) {
                    try {
                        userLoggato = login(message);     //try login
                        if (userLoggato) {      //if ( login is correct )
                            this.send("Login effettuato con successo! ");
                            this.send("Benvenuto " + usernameControl);
                            mainButton();
                        } else
                            this.send(""" 
                                    Errore col login,
                                    provare nuovamente cliccando : /login""");

                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }


                else if (message.startsWith("Register = ") || message.startsWith("register = ")) {
                    try {
                        userLoggato = register(message);     //try login
                        if (userLoggato) {      //if ( register is correct )
                            this.send("Registrazione effettuata con successo! ");
                            this.send("Benvenuto " + usernameControl);
                            userLoggato = true;
                            mainButton();
                        } else
                            this.send("""
                                    Errore con la registrazione,
                                    provare nuovamente cliccando :\s
                                    /register""");

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }


                else {

                    switch (message) {
                        case "/start" -> this.send("""
                                Grazie per essere entrato in NabooNews.
                                \nPer vedere le notizie è necessario autenticarsi.
                                                                
                                Per il login usare il seguente formato :
                                ( ex. : Login = username password )
                                                                
                                oppure registrarsi usando il seguente formato :
                                ( ex. : Register = username password password)""");
                        case "/login" ->
                                this.send("Inserire username e password.\n( ex. : Login = username password )");
                        case "/register" ->
                                this.send("Inserire username password e confermaPassword.\n( ex. : Register = username password passoword)");
                        case "/stop" -> {
                                botSession.stop();
                                this.send("Bot spento");
                            }
                        default ->
                                this.send("\uD83D\uDC49\uD83C\uDFFCScusa, non ho capito la richiesta. Selezionare il comando : \"/start");
                    }
                }

            }

            else {   //utenteLoggato == true

                if (booleanCategories == true) {
                    if (message.equalsIgnoreCase("Si")) {
                        this.send("Scegliere la nuova categoria");
                    }
                    else if(message.equalsIgnoreCase("No")) {
                        booleanCategories = false;
                        mainButton();
                    }
                    else {
                        try {
                            filterCategory(message);
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

                else if (booleanKeyword == true) {
                    if (message.equalsIgnoreCase("Si")) {
                        this.send("Scegliere la nuova parola chiave");
                    } else if(message.equalsIgnoreCase("No")) {
                        booleanKeyword = false;
                        mainButton();
                    } else {
                        try {
                            filterKeyword(message);
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

                else if (booleanAuthors == true) {
                    if (message.equalsIgnoreCase("Si")) {
                        this.send("Scegliere il nuovo autore");
                    } else if(message.equalsIgnoreCase("No")) {
                        booleanAuthors = false;
                        mainButton();
                    } else {
                        try {
                            filterAuthors(message);
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }

                }

                else if (booleanTime == true) {
                    if (message.equalsIgnoreCase("Si")) {
                        this.send("Scegliere una nuovo data");
                    } else if(message.equalsIgnoreCase("No")) {
                        booleanTime = false;
                        mainButton();
                    } else {
                        try {
                            filterTime(message);
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }

                }

                else if (booleanVoto == true) {

                    int voto = Integer.parseInt(message);

                    if (voto <= 5 && voto >= 1) {
                        try {
                            commento_voto.writeFileVoti(news.getTitle(), usernameControl, voto);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        booleanVoto = false;
                        news = null;
                        this.send("Grazie.");
                    } else
                        this.send("Inserisci un voto\n(da 1 a 5)");

                }

                else if (booleanCommento == true) {
                    try {
                        commento_voto.writeFileCommento(news.getTitle(), usernameControl, message);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    booleanCommento = false;
                    news = null;
                    this.send("Grazie.");

                }

                else {

                    switch (message) {
                        case "/logout" -> {
                            usernameControl = null;
                            passwordControl = null;
                            userLoggato = false;
                            this.send("Logout effettuato.");
                        }
                        case "/news" -> {
                            Message message2 = update.getCallbackQuery().getMessage();
                            SendMessage sendMessage = new SendMessage();
                            sendMessage.setChatId(message2.getChatId().toString());
                            try {
                                news(sendMessage);
                            } catch (IOException | FeedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        case "/stop" -> {
                            botSession.stop();
                            this.send("Bot spento");
                        }
                        case "/menu" -> mainButton();
                        default ->
                                this.send("\uD83D\uDC49\uD83C\uDFFCScusa, non ho capito la richiesta. Selezionare il comando : \"/start");
                    }
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
                    sendMessage.setText("Inserisci un commento ");         //send message
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }

                case "CallVota" -> {
                    returnNotizia(update.getCallbackQuery().getMessage().getText());
                    booleanVoto = true;
                    sendMessage.setText("Inserisci un voto\n(da 1 a 5)");
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }

                case "CallShowComment&Vote" -> {
                    returnNotizia(update.getCallbackQuery().getMessage().getText());
                    try {
                        sendMessage.setText("Titolo della notizia : \n" + news.getTitle() + commento_voto.getInfo(news));

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

                case "CallCommand" -> {
                    sendMessage.setText("""
                                /start : messaggio iniziale;
                                \n/login : richiesta di login;
                                \n/register : richiesta di registrazione;
                                \n/news : visuallizzazione delle notizie;
                                \n/menu : visualizzazione bottoni principali;
                                \n/stop : stop bot;
                                \n/logout : logout utente.""");
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }


                case "CallNotizie" -> {
                    try {
                        news(sendMessage);
                    } catch (IOException | FeedException e) {
                        throw new RuntimeException(e);
                    }

                }

                case "CallFilter" -> filtering(sendMessage);

                case "CallCategory" -> {
                    booleanCategories = true;
                    sendMessage.setText("Inserire una categoria\n" +
                            "(ex.: \"Economia\", \"Cultura\", \"Scienze\", ...)");
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }

                case "CallAuthors" -> {
                    booleanAuthors = true;
                    sendMessage.setText("Inserire un autore\n" +
                            "(ex. : \"Aldo Cazzullo\", \"Ida Bozzi\", \"Antonio Carioti\", ...)");
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }

                case "CallTime" -> {
                    booleanTime = true;
                    sendMessage.setText("Inserire la data\n" +
                            "(ex. : \"22-11-2022\")");
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }

                case "CallKeyword" -> {
                    booleanKeyword = true;
                    sendMessage.setText("Inserire la parola chiave");
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

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();   //creiamo struttura bottoni
        List<List<InlineKeyboardButton>> inlineKeyboard = new ArrayList<>();    //creiamo lista di righe


        List<InlineKeyboardButton> firstButtonRow = new ArrayList<>();   //creiamo una riga dove inserire i bottoni
        List<InlineKeyboardButton> secondButtonRow = new ArrayList<>();            //creiamo una seconda riga
        InlineKeyboardButton buttonCategoy = new InlineKeyboardButton("\uD83D\uDCC8\u26BDCategoria");     //button per filtro categoria
        InlineKeyboardButton buttonAuthor = new InlineKeyboardButton("\uD83D\uDE4DAutore");       //button per autore
        InlineKeyboardButton buttonDate = new InlineKeyboardButton("\u231BPer data");       //button per data
        InlineKeyboardButton buttonKeyword = new InlineKeyboardButton("\uD83D\uDD24Parola chiave");       //button per parola chiave

        buttonCategoy.setCallbackData("CallCategory");         //risposta al click sul bottone
        buttonAuthor.setCallbackData("CallAuthors");
        buttonDate.setCallbackData("CallTime");
        buttonKeyword.setCallbackData("CallKeyword");

        firstButtonRow.add(buttonCategoy);       //aggiungiamo i bottoni alle righe create
        firstButtonRow.add(buttonAuthor);
        secondButtonRow.add(buttonDate);
        secondButtonRow.add(buttonKeyword);
        inlineKeyboard.add(firstButtonRow);    //aggiungiamo la riga alla lista di righe
        inlineKeyboard.add(secondButtonRow);
        keyboardMarkup.setKeyboard(inlineKeyboard);

        sendMessage.setText("Seziona il filtro");
        sendMessage.setReplyMarkup(keyboardMarkup);  //inseriamo al messaggio la struttura dei bottoni

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    private void filterAuthors(String author) throws FileNotFoundException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(this.update.getMessage().getChatId()));

        notizia = loadNews();

        boolean trova = false;

        for (Notizia n : notizia) {
            if (n == null) {
                break;
            } else if (n.getAutore().equalsIgnoreCase(author)) {
                trova = true;
                sendMessage.setText(n.toString());
                sendMessage.setReplyMarkup(buttonCommentiVoti());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
        if (!trova) {
            this.send("Nessun risultato trovato per " + author);
            this.send("Inserire un nuovo autore ? \n(Si/No)");
        } else {
            booleanAuthors = false;
            sendMessage.setReplyMarkup(null);
            sendMessage.setText("\u293E/menu");
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

        notizia = loadNews();

        boolean trova = false;

        for (Notizia n : notizia) {
            if (n == null) {
                break;
            } else if (n.getCategory().equalsIgnoreCase(category)) {
                trova = true;
                sendMessage.setText(n.toString());
                sendMessage.setReplyMarkup(buttonCommentiVoti());

                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }


        if (!trova) {
            this.send("Nessun risultato trovato per " + category);
            this.send("Inserire una nuova categoria ? \n(Si/No)");
        } else {
            booleanCategories = false;
            sendMessage.setReplyMarkup(null);
            sendMessage.setText("\u293E/menu");
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

        notizia = loadNews();

        boolean trova = false;

        for (Notizia n : notizia) {
            if (n == null) {
                break;
            } else if (n.getTitle().contains(keyword) || n.getDescrizione().contains(keyword)) {
                trova = true;
                sendMessage.setText(n.toString());
                sendMessage.setReplyMarkup(buttonCommentiVoti());

                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }

        if (!trova) {
            this.send("Nessun risultato trovato con la parola chiave \"" + keyword + "\"");
            this.send("Inserire una nuova parola chiave ? \n(Si/No)");
        } else {
            booleanKeyword = false;
            sendMessage.setReplyMarkup(null);
            sendMessage.setText("\u293E/menu");
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

        notizia = loadNews();

        Date dataNews = new Date();
        dataNews.setDate(Integer.parseInt(time.substring(0,2)));
        dataNews.setMonth(Integer.parseInt(time.substring(3,5))-1);   //i mesi partono da indice 0
        dataNews.setYear(Integer.parseInt(time.substring(6)) - 1900);    //il conto degli anni parte da 1900
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
                sendMessage.setReplyMarkup(buttonCommentiVoti());

                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

        }

        if (!trova) {
            this.send("Nessun risultato per " + time);
            this.send("Inserire una nuova data ? \n(Si/No)");
        } else {
            booleanTime = false;
            sendMessage.setReplyMarkup(null);
            sendMessage.setText("\u293E/menu");
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


    private void news(SendMessage sendMessage) throws IOException, FeedException {

        GestoreNotizia gestoreNews = new GestoreNotizia();
        gestoreNews.caricaNotizie();

        notizia = loadNews();

        int contatore = 0;

        for (Notizia n : notizia) {
            if (contatore == 10 || (n == null )) {
                break;
            }

            contatore++;
            sendMessage.setText(n.toString());
            sendMessage.setReplyMarkup(buttonCommentiVoti());

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }

        sendMessage.setReplyMarkup(null);
        sendMessage.setText("\u293E/menu");
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    private boolean register(String messaggio) throws IOException {  //riceviamo richiesta registrazione

        //String path ="C:\\Users\\feder\\IdeaProjects\\Progetto-Naboo\\src\\main\\resources\\com\\example\\nabo\\DataBase\\Dati.json";
        String path = "main/resources/com/example/nabo/DataBase/Dati.json" ;
        String confermaPassword;

        messaggio = messaggio.substring(11);  //username password confPassword

        StringTokenizer string = new StringTokenizer(messaggio);
        if (string.countTokens() == 3) {   //verifichiamo di avere 3 campi
            usernameControl = string.nextToken();
            passwordControl = string.nextToken();
            confermaPassword = string.nextToken();
            if(!passwordControl.equals(confermaPassword))   //verifichiamo che le due password coincidano
                return userLoggato;
        } else {
            return userLoggato;
        }


        JsonReader leggi = new JsonReader(new FileReader(path));
        Gson gson = new Gson();
        Utenti = gson.fromJson(leggi, (new TypeToken<List<Utente>>() {
        }).getType());

        for (Utente control : Utenti) {
            if (usernameControl.equals(control.getUsername())) {  //verifichiamo che non esistano altri utenti con lo stesso username
                return userLoggato;
            }
        }


        UserRegistrationController userReg = new UserRegistrationController();

        userReg.writeFile(usernameControl, passwordControl, false );   //possiamo scrivere i dati sul file json
        userLoggato = true;
        return userLoggato;
    }



    private boolean login(String messaggio) throws FileNotFoundException {

        boolean login = false;

        messaggio = messaggio.substring(8);
        StringTokenizer string = new StringTokenizer(messaggio);
        if ((string.countTokens() == 2)) {       //controllo che la stringa ricevuta abbia 2 campi
            usernameControl = string.nextToken();
            passwordControl = string.nextToken();
        } else
            return login;



        JsonReader leggi = new JsonReader(new FileReader("main/resources/com/example/nabo/DataBase/Dati.json"));
        Gson gson = new Gson();
        Utenti = gson.fromJson(leggi, (new TypeToken<List<Utente>>() {
        }).getType());

        for (Utente control : Utenti) {
            if (usernameControl.equals(control.getUsername()) && passwordControl.equals(control.getPassword())) {
                login = true;
                break;
            }
        }

        return login;
    }

    private void mainButton() {
        if (update.hasMessage()) {                  //aggiorna : ho un messaggio ? TRUE

            Message message = update.getMessage();  // inserisco nella variabile messagge il messaggio ricevuto

            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(String.valueOf(message.getChatId()));

            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> inlineButtons = new ArrayList<>();

            List<InlineKeyboardButton> firstBottonRow = new ArrayList<>();
            List<InlineKeyboardButton> secondBottonRow = new ArrayList<>();

            /// InlineButton 1
            InlineKeyboardButton newsButton = new InlineKeyboardButton();   //nuovo bottone
            newsButton.setText("\uD83D\uDCF0Vedi notizie");
            newsButton.setCallbackData("CallNotizie");
            firstBottonRow.add(newsButton);             //aggiungo a riga 1

            // InlineButton 2
            InlineKeyboardButton filterButton = new InlineKeyboardButton();
            filterButton.setText("\uD83D\uDCCAFiltra notizie");
            filterButton.setCallbackData("CallFilter");
            firstBottonRow.add(filterButton);

            // InlineButton 3
            InlineKeyboardButton commandButton = new InlineKeyboardButton();
            commandButton.setText("\u2699Comandi");
            commandButton.setCallbackData("CallCommand");
            secondBottonRow.add(commandButton);

            inlineButtons.add(firstBottonRow);      //aggiungo bottoneRiga1
            inlineButtons.add(secondBottonRow);     //aggiungo bottoneRiga2

            inlineKeyboardMarkup.setKeyboard(inlineButtons);

            sendMessage.setText("Selezionare un comando");
            sendMessage.setReplyMarkup(inlineKeyboardMarkup);

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }

    private InlineKeyboardMarkup buttonCommentiVoti(){
        InlineKeyboardMarkup keyboardVoteCommentMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> inlineKeybVoteComment = new ArrayList<>();


        List<InlineKeyboardButton> inlineKeyboardButtonsVeC = new ArrayList<>();   //creiamo una riga dove inserire i bottoni
        List<InlineKeyboardButton> secondBottonRow = new ArrayList<>();            //creiamo una seconda riga dove vedere i risultati dei commenti e voti
        InlineKeyboardButton buttonVote = new InlineKeyboardButton("\uD83D\uDC4Dvota");     //button vote
        InlineKeyboardButton buttonComment = new InlineKeyboardButton("\uD83D\uDCACcommenta");       //button commenti
        InlineKeyboardButton buttonShowComment = new InlineKeyboardButton("mostra voti e commenti");       //button mostra commenti

        buttonVote.setCallbackData("CallVota");         // risposta al click sul bottone
        buttonComment.setCallbackData("CallCommenta");
        buttonShowComment.setCallbackData("CallShowComment&Vote");

        inlineKeyboardButtonsVeC.add(buttonVote);       //aggiungiamo i due bottoni alla riga creata
        inlineKeyboardButtonsVeC.add(buttonComment);
        secondBottonRow.add(buttonShowComment);
        inlineKeybVoteComment.add(inlineKeyboardButtonsVeC);    //aggiungiamo la riga alla lista di righe
        inlineKeybVoteComment.add(secondBottonRow);
        keyboardVoteCommentMarkup.setKeyboard(inlineKeybVoteComment);

        return keyboardVoteCommentMarkup;
    }


    private List<Notizia> loadNews() throws FileNotFoundException {

        JsonReader read = new JsonReader(new FileReader("main/resources/com/example/nabo/DataBase/Info-Notizie.json"));

        Gson gson = new Gson();
        notizia = gson.fromJson(read, (new TypeToken<List<Notizia>>() {
        }).getType());

        return notizia;
    }


    private void send(String msg) {
        SendMessage send = new SendMessage();
        send.setChatId(this.update.getMessage().getChatId().toString());

        send.setText(msg);

        try {
            execute(send);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public static BotSession getSession() {
        return botSession;
    }

    public static void setSession(BotSession session) {
        botSession = session;
    }

}