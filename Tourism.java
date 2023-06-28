import java.io.*;
import java.lang.reflect.Field;
import java.time.LocalDate;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;


class add_data implements Serializable{
    String name;
    String email;
    String phone;
    String seat;
    add_data(){

    }
    add_data(String n,String e,String p,String se){
        name=n;
        email=e;
        phone=p;
        seat=se;

    }
    void read_handling(){

        try {
            FileInputStream f1 = new FileInputStream("BookStore.ser");
            ObjectInputStream in = new ObjectInputStream(f1);

            while (true) {
                try {
                    add_data print = (add_data) (in.readObject());
                    System.out.println("Name: " + print.getName());
                    System.out.println("email: " + print.getEmail());
                    System.out.println("phone: " + print.getPhone());
                    System.out.println("Seat: "+print.getSeat());
                    System.out.println("--------------------");
                }

                catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }

        catch (EOFException ex) { // This exception will be caught when EOF isreached
            System.out.println("End of file reached.");
        }

        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    String getName(){
        return name;
    }
    String getEmail(){
        return email;
    }
    String getPhone(){
        return phone;
    }
    String getSeat(){
        return seat;
    }
}
class file_handling{
    String file;
    String p;
    String na;
    String orig_pass;
    Ticket_info ticket;
    file_handling(){

    }
    file_handling(String file_name,String name,String password,String original_pass,Ticket_info t){
        file=file_name;
        p=password;
        na=name;
        ticket=t;
        orig_pass=original_pass;
        if(check(file,na,p)){
            new user(orig_pass,ticket);
        }
        else {
            new gui_startup(orig_pass,ticket);
        }

    }
    boolean check(String filename,String name,String password){
        try{
            FileReader fileReader=new FileReader(filename);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            String line;
            // while ((line = reader.readLine()) != null)
            while ((line=bufferedReader.readLine())!=null){
                String arr[] =line.split(",");
                String pass=arr[1];
                String email=arr[0];
                if(password.equals(pass)&&name.equals(email)){
                    System.out.println(arr[0]+"   "+arr[1]);
                    return true;

                }
            }

            bufferedReader.close();
        }
        catch (IOException any){
            System.out.println();
            any.printStackTrace();
        }
        return false;
    }
}
abstract class e{
    Ticket_info ticket;
    String pass;
    abstract void display();
    abstract void destroy();

}
class extra extends e{

    extra(){

    }
    extra(String p,Ticket_info t){
        ticket=t;
        pass=p;
    }


    @Override
    void display() {
        ticket.setVisible(false);
        new gui_startup(pass,ticket);
    }

    @Override
    void destroy() {

    }
}

class Ticket_info extends JFrame implements ActionListener{
    JLabel name,email,phone,seat;
    JFrame frame;
    JButton next,back;
    //    String arrival="-",departure="-";
    JTextField name_field,email_field,phone_field,seat_field;

    JTextField gernate_price;
    String arrival="-",departure="Islamabad";
    JLabel place;
    String store_value;
    String price_value;
    String p;
    Ticket_info ticket;
    void add_combo(String g_arrival,String get_departure,String item){
        comboBox.addItem(item);
        store_value=item;
        arrival=g_arrival;
        departure=get_departure;
    }
    void give_price_of_ticket(String item){
        price_value=item;

    }


    Font f=new Font("Algerian",Font.BOLD,40);
    Font f1=new Font("Algerian",Font.BOLD,50);
    Font f2=new Font("Arial",Font.BOLD,20);
    JComboBox<String> comboBox;
    Ticket_info(){


        this.setLayout(null);
        this.setSize(2000,2000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon=new ImageIcon("dawoo.png");
        JLabel label=new JLabel(icon);
        label.setBounds(100,0,700,500);
//        JPanel panel=new JPanel();
//        panel.add(label);
//        panel.setBounds(100,0,700,500);
        this.add(label);

        //req
        JLabel req=new JLabel("Please confirm your seat");
        req.setBounds(800, 100, 800, 40);
        req.setForeground(Color.RED);
        req.setFont(f1);
        this.add(req);

        name = new JLabel("First name:");
        name.setBounds(850, 150, 250, 40);
        name.setFont(f);
        this.add(name);

        email = new JLabel("email: ");
        email.setBounds(850, 250, 250, 40);
        email.setFont(f);
        this.add(email);

        phone = new JLabel("phone: ");
        phone.setBounds(850, 350, 250, 40);
        phone.setFont(f);
        this.add(phone);

        seat = new JLabel("seat: ");
        seat.setBounds(850, 450, 250, 40);
        seat.setFont(f);
        this.add(seat);

        place = new JLabel("Enter the station: ");
        place.setBounds(850, 520, 550, 40);
        place.setFont(f);
        this.add(place);


        comboBox = new JComboBox<>(new String[]{"Stations", "Islamabad to Sargodha", "Islamabad to faisalabad","Islamabad to Kashmir"});
        comboBox.setBounds(850,600,250,40);
        comboBox.addActionListener(this);
        this.add(comboBox);

        //=-------------------------------  text fields     -------------------------
        name_field=new JTextField();
        name_field.setBounds(1100,150,150,40);
        name_field.setFont(f2);
        this.add(name_field);

        email_field=new JTextField();
        email_field.setBounds(1000,250,150,40);
        email_field.setFont(f2);
        this.add(email_field);

        phone_field=new JTextField();
        phone_field.setBounds(1000,350,150,40);
        phone_field.setFont(f2);
        this.add(phone_field);

        seat_field=new JTextField();
        seat_field.setBounds(1000,450,150,40);
        seat_field.setFont(f2);
        this.add(seat_field);

        //---------------------------------     Buttons     ----------------------------
        back=new JButton("<- Back");
        back.setFocusable(false);
        back.setBounds(900,700,200,60);
        back.setFont(f);
        back.addActionListener(this);
        this.add(back);

        next=new JButton("Confirm ->");
        next.setFocusable(false);
        next.setBounds(1200,700,250,60);
        next.setFont(f);
        next.addActionListener(this);
        this.add(next);





        //this.setVisible(true);
        this.dispose();

    }


    //---------------------------------------        constuctor       ---------------------------------------
    Ticket_info(String a,Ticket_info b){
        ticket=b;
        p=a;
        ticket.setVisible(true);

        JFrame frame1=new JFrame();
        frame1.setLayout(null);
        frame1.setSize(2000,2000);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon=new ImageIcon("dawoo.png");
        JLabel label=new JLabel(icon);
        label.setBounds(100,0,700,500);
//        JPanel panel=new JPanel();
//        panel.add(label);
//        panel.setBounds(100,0,700,500);
        frame1.add(label);

        //req
        JLabel req=new JLabel("Please confirm your seat");
        req.setBounds(800, 100, 800, 40);
        req.setForeground(Color.RED);
        req.setFont(f1);
        frame1.add(req);

        name = new JLabel("First name:");
        name.setBounds(850, 150, 250, 40);
        name.setFont(f);
        frame1.add(name);

        email = new JLabel("email: ");
        email.setBounds(850, 250, 250, 40);
        email.setFont(f);
        frame1.add(email);

        phone = new JLabel("phone: ");
        phone.setBounds(850, 350, 250, 40);
        phone.setFont(f);
        frame1.add(phone);

        seat = new JLabel("seat: ");
        seat.setBounds(850, 450, 250, 40);
        seat.setFont(f);
        frame1.add(seat);

        place = new JLabel("Enter the station: ");
        place.setBounds(850, 520, 550, 40);
        place.setFont(f);
        frame1.add(place);


        comboBox = new JComboBox<>(new String[]{"Stations", "Islamabad to Sargodha", "Islamabad to faisalabad","Islamabad to Kashmir"});
        comboBox.setBounds(850,600,250,40);
        comboBox.addActionListener(this);
        frame1.add(comboBox);

        //=-------------------------------  text fields     -------------------------
        name_field=new JTextField();
        name_field.setBounds(1100,150,150,40);
        name_field.setFont(f2);
        frame1.add(name_field);

        email_field=new JTextField();
        email_field.setBounds(1000,250,150,40);
        email_field.setFont(f2);
        frame1.add(email_field);

        phone_field=new JTextField();
        phone_field.setBounds(1000,350,150,40);
        phone_field.setFont(f2);
        frame1.add(phone_field);

        seat_field=new JTextField();
        seat_field.setBounds(1000,450,150,40);
        seat_field.setFont(f2);
        frame1.add(seat_field);

        //---------------------------------     Buttons     ----------------------------
        back=new JButton("<- Back");
        back.setFocusable(false);
        back.setBounds(900,700,200,60);
        back.setFont(f);
        back.addActionListener(this);
        frame1.add(back);

        next=new JButton("Confirm ->");
        next.setFocusable(false);
        next.setBounds(1200,700,250,60);
        next.setFont(f);
        next.addActionListener(this);
        frame1.add(next);





//        this.setVisible(true);
        // frame.dispose();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==next){
            System.out.println("Your ticket has been registered");
            print_ticket();
            //name,email,phoen,seat
            String name=name_field.getText();
            String email=email_field.getText();
            String phone=phone_field.getText();
            String seat=seat_field.getText();
            add_data a=new add_data(name,email,phone,seat);
            //now add this methord in the file
            try {
                ObjectOutputStream oo=new ObjectOutputStream(new FileOutputStream("BookStore.ser"));
                oo.writeObject(a);
                oo.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            a.read_handling();




            this.dispose();
        }

        else if(e.getSource()==comboBox) {
            gernate_price = new JTextField();
            gernate_price.setBounds(850, 650, 150, 40);
            gernate_price.setFocusable(false);
            String get_combo=(String) comboBox.getSelectedItem();

            int ticket;
            if (!get_combo.equals("Stations")) {

                if (get_combo.equals("Islamabad to Sargodha")) {
                    arrival="Sargodha";
                    ticket = 1500;
                    gernate_price.setText("");
                    gernate_price.setText("Your ticket price is " + ticket);
                } else if (get_combo.equals("Islamabad to faisalabad")) {
                    ticket = 1800;
                    arrival="Faisalabad";
                    gernate_price.setText("");
                    gernate_price.setText("Your ticket price is " + ticket);
                } else if (get_combo.equals("Islamabad to Kashmir")) {
                    arrival="Kashmir";
                    ticket = 2500;
                    gernate_price.setText("");
                    gernate_price.setText("Your ticket price is " + ticket);
                }
                else if(get_combo.equals(store_value)){

                    int n=Integer.parseInt(price_value);
                    gernate_price.setText("Your ticket price is "+n);

                }
                this.add(gernate_price);

            } else {
                gernate_price.setBounds(0, 0, 0, 0);

            }
        }
        else if(e.getSource()==back){
            this.setVisible(false);
            //gui_startup a=new gui_startup(p,ticket);

        }


    }
    public void print_ticket(){
        JLabel from=new JLabel();
        from.setText(departure);
        Font f1=new Font("Arial",Font.BOLD,24);
        from.setFont(f1);
        from.setBounds(400, 110, 200, 100);

        JLabel to=new JLabel();
        to.setText(arrival);
        to.setFont(f1);
        to.setBounds(400, 160, 200, 100);

        //--------------------------        Date            -------------------------
        LocalDate date= LocalDate.now();
        int d,m,y;
        d=date.getDayOfMonth();
        m=date.getMonthValue();
        y=date.getYear();
        String dat;
        dat=d+"/"+m+"/"+y;

        JLabel curr_date=new JLabel(dat);
        curr_date.setText(dat);
        curr_date.setFont(f1);
        curr_date.setBounds(500, 220, 200, 100);

        //----------------------------      Time        ---------------------------------
        Calendar c=Calendar.getInstance();
        int hour=c.get(Calendar.HOUR);
        int min=c.get(Calendar.MINUTE);
        String time=hour+" : "+min;

        JLabel t=new JLabel(time);
        t.setFont(f1);
        t.setBounds(680, 270, 200, 100);


//        JLabel name = new JLabel();
//        name.setText("abdul ahad");
//        Font f1=new Font("Arial",Font.BOLD,24);
//        name.setFont(f1);
//        name.setBounds(400, 110, 200, 100);
        ImageIcon icon = new ImageIcon("bus_empty.png");


        JFrame f = new JFrame();
        f.setLayout(null);
        f.setSize(1500, 1500);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, f.getWidth(), f.getHeight());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(layeredPane);

        JPanel panel = new JPanel();
        panel.setBounds(100, 0, 1200, 800);
        layeredPane.add(panel, Integer.valueOf(0)); // Add panel at index 0 (back layer)

        JLabel label = new JLabel(icon);
        panel.add(label);

        layeredPane.add(from, Integer.valueOf(1)); // Add name label at index 1 (front layer)
        layeredPane.add(to, Integer.valueOf(1)); // Add name label at index 1 (front layer)
        layeredPane.add(curr_date, Integer.valueOf(1)); // Add name label at index 1 (front layer)
        layeredPane.add(t, Integer.valueOf(1)); // Add name label at index 1 (front layer)


        f.setVisible(true);
    }


}

class Admin_password{
    String email="ahad";
    String password="123";
    Admin_password(){

    }
    Admin_password(String a,String b){
        email=a;
        password=b;
    }



    void set_email(String emil){
        this.email=emil;
    }
    String get_password(){
        return password;
    }
    void  set_password(String p){
        password=p;
    }
}
class Admin extends Admin_password implements ActionListener  {
    JFrame f;

    JLabel price,terminal;
    JLabel old_password,new_password;
    JTextField field_old,field_new;
    JTextField price_field;
    JButton location_add_button,price_add_button,change_password_add_button;
    Font f1=new Font("Arial",Font.BOLD,34);
    JLabel L,L2;
    JTextField arrival_field,departure_field;
    JLabel arrival_label,departure_label;
    Admin_password change_pass;
    Ticket_info change_price;
    JButton Confirm;
    JButton back;
    private boolean password_bool;
    String store_password;
    String p;
    void display(){

        change_price.setVisible(true);
    }
    void destroy(){
        change_price.setVisible(false);
        new gui_startup(p,change_price);
    }
    Admin() {
        //--------------------      Admin       ------------------------
        f = new JFrame();
        ImageIcon icon=new ImageIcon("privacy.png");
        L=new JLabel(icon);
        L.setBounds(100,50,600,700);
        f.add(L);

        ImageIcon icon2=new ImageIcon("dawoo.png");
        L2=new JLabel(icon2);
        L2.setBounds(800,50,600,700);
        f.add(L2);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setLayout(null);
        //-----------------------   Adding the buttons
        location_add_button=new JButton("Add Terminal");
        location_add_button.setFont(f1);
        location_add_button.setBounds(800,50,600,50);
        location_add_button.setBackground(Color.RED);
        location_add_button.setFocusable(false);
        location_add_button.setForeground(Color.BLACK);
        location_add_button.addActionListener(this);
        f.add(location_add_button);

        change_password_add_button=new JButton("Password change");
        change_password_add_button.setFont(f1);
        change_password_add_button.setBounds(100,50,600,50);
        change_password_add_button.setBackground(Color.BLUE);
        change_password_add_button.setFocusable(false);
        change_password_add_button.setForeground(Color.BLACK);
        change_password_add_button.addActionListener(this);
        f.add(change_password_add_button);

        Confirm=new JButton("Confirm");
        Confirm.setFont(f1);
        Confirm.setBounds(600,740,200,50);
        Confirm.setBackground(Color.GREEN);
        Confirm.setFocusable(false);
        Confirm.setForeground(Color.BLACK);
        Confirm.addActionListener(this);
        f.add(Confirm);

        back=new JButton("back");
        back.setFont(f1);
        back.setBounds(300,740,200,50);
        back.setBackground(Color.GREEN);
        back.setFocusable(false);
        back.setForeground(Color.BLACK);
        back.addActionListener(this);
        f.add(back);

        //-------------------------     Labels  ----------------
        old_password=new JLabel("Old password:");
        old_password.setFont(f1);
        old_password.setBounds(0,0,0,0);
        f.add(old_password);

        new_password=new JLabel("New password:");
        new_password.setFont(f1);
        new_password.setBounds(0,0,0,0);
        f.add(new_password);

        price=new JLabel("Enter the new Price:");
        price.setFont(f1);
        price.setBounds(0,0,0,0);
        f.add(price);

        //------------------------2 Labels
        arrival_label=new JLabel(" arrival place:");
        arrival_label.setFont(f1);
        price.setBounds(0,0,0,0);
        f.add(arrival_label);

        departure_label=new JLabel(" departure place:");
        departure_label.setFont(f1);
        departure_label.setBounds(0,0,0,0);
        f.add(departure_label);

        //-------------------------     jtextfields ---------------
        field_old=new JTextField();
        field_old.setBounds(0,0,0,0);
        f.add(field_old);

        field_new=new JTextField();
        field_new.setBounds(0,0,0,0);
        f.add(field_new);

        price_field=new JTextField();
        price_field.setBounds(0,0,0,0);
        f.add(price_field);

        //------------------------------    2 textfields
        arrival_field=new JTextField();
        arrival_field.setBounds(0,0,0,0);
        f.add(arrival_field);

        departure_field=new JTextField();
        departure_field.setBounds(0,0,0,0);
        f.add(departure_field);

        //--------------------  Grant verson

        f.setVisible(true);
    }
    Admin(String pass,Ticket_info ticket){
        change_price=ticket;
        p=pass;

        //--------------------      Admin       ------------------------
        f = new JFrame();
        ImageIcon icon=new ImageIcon("privacy.png");
        L=new JLabel(icon);
        L.setBounds(100,50,600,700);
        f.add(L);

        ImageIcon icon2=new ImageIcon("dawoo.png");
        L2=new JLabel(icon2);
        L2.setBounds(800,50,600,700);
        f.add(L2);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setLayout(null);
        //-----------------------   Adding the buttons
        location_add_button=new JButton("Add Terminal");
        location_add_button.setFont(f1);
        location_add_button.setBounds(800,50,600,50);
        location_add_button.setBackground(Color.RED);
        location_add_button.setFocusable(false);
        location_add_button.setForeground(Color.BLACK);
        location_add_button.addActionListener(this);
        f.add(location_add_button);

        change_password_add_button=new JButton("Password change");
        change_password_add_button.setFont(f1);
        change_password_add_button.setBounds(100,50,600,50);
        change_password_add_button.setBackground(Color.BLUE);
        change_password_add_button.setFocusable(false);
        change_password_add_button.setForeground(Color.BLACK);
        change_password_add_button.addActionListener(this);
        f.add(change_password_add_button);

        Confirm=new JButton("Confirm");
        Confirm.setFont(f1);
        Confirm.setBounds(600,740,200,50);
        Confirm.setBackground(Color.GREEN);
        Confirm.setFocusable(false);
        Confirm.setForeground(Color.BLACK);
        Confirm.addActionListener(this);
        f.add(Confirm);

        //-------------------------     Labels  ----------------
        old_password=new JLabel("Old password:");
        old_password.setFont(f1);
        old_password.setBounds(0,0,0,0);
        f.add(old_password);

        new_password=new JLabel("New password:");
        new_password.setFont(f1);
        new_password.setBounds(0,0,0,0);
        f.add(new_password);

        price=new JLabel("Enter the new Price:");
        price.setFont(f1);
        price.setBounds(0,0,0,0);
        f.add(price);

        //------------------------2 Labels
        arrival_label=new JLabel(" arrival place:");
        arrival_label.setFont(f1);
        price.setBounds(0,0,0,0);
        f.add(arrival_label);

        departure_label=new JLabel(" departure place:");
        departure_label.setFont(f1);
        departure_label.setBounds(0,0,0,0);
        f.add(departure_label);

        //-------------------------     jtextfields ---------------
        field_old=new JTextField();
        field_old.setBounds(0,0,0,0);
        f.add(field_old);

        field_new=new JTextField();
        field_new.setBounds(0,0,0,0);
        f.add(field_new);

        price_field=new JTextField();
        price_field.setBounds(0,0,0,0);
        f.add(price_field);

        //------------------------------    2 textfields
        arrival_field=new JTextField();
        arrival_field.setBounds(0,0,0,0);
        f.add(arrival_field);

        departure_field=new JTextField();
        departure_field.setBounds(0,0,0,0);
        f.add(departure_field);

        //--------------------  Grant verson

        back=new JButton("back");
        back.setFont(f1);
        back.setBounds(300,740,200,50);
        back.setBackground(Color.GREEN);
        back.setFocusable(false);
        back.setForeground(Color.BLACK);
        back.addActionListener(this);
        f.add(back);

        f.setVisible(true);


    }//end of constructor

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==change_password_add_button){
            //---------------   images      ---------------
            L.setBounds(0,0,0,0);
            L2.setBounds(800,50,600,800);
            //---------------   labels  ----------------
            old_password.setBounds(130,180,300,40);

            new_password.setBounds(130,300,300,40);
            //---------------   JTextfield  -----------------------
            field_old.setBounds(130,230,300,40);

            field_new.setBounds(130,360,300,40);
            password_bool=true;
            //---------         set the new password plus the old one

        }
        else if(e.getSource()==back){
            String pass= field_new.getText();
            new LoginPortal(p,change_price);
            f.setVisible(false);
        }
        else if(e.getSource()==Confirm && password_bool){
            String pass=field_old.getText();
            if(pass.equals(password)){
                String pass2=field_new.getText();
                if(!pass.equals("")) {
                    password = pass2;
                }

                System.out.println(password);
                new LoginPortal(password,change_price);
                f.setVisible(false);
            }
            else {
                new LoginPortal(p,change_price);
                f.setVisible(false);
            }


        }
        else if(e.getSource()==Confirm && !password_bool){
            //--------------        Station set -----------------------
            change_price.comboBox.addItem("ok");

            String get_arrival= arrival_field.getText();
            String get_departure=departure_field.getText();
            String concat_both=(get_departure+" to "+get_arrival);
            change_price.add_combo(get_arrival,get_departure,concat_both);

            //--------------------      PRICE   ------------
            String price=price_field.getText();
            change_price.give_price_of_ticket(price);

            new LoginPortal(p,change_price);
            f.setVisible(false);
        }


        else if(e.getSource()==location_add_button){
            //--------------------------        images  -------------------
            L2.setBounds(0,0,0,0);
            L.setBounds(100,50,600,800);

            //-------------------   price       -------------------
            price.setBounds(800,500,400,40);
            price_field.setBounds(800,540,200,40);

            //------------          arrival
            arrival_label.setBounds(800,200,400,40);
            arrival_field.setBounds(800,250,400,40);

            //-----------           departure       ---------------------
            departure_label.setBounds(800,320,400,40);
            departure_field.setBounds(800,370,400,40);
            //------------------r   set remaining to the null
            field_old.setText("");
            field_new.setText("");
            //--------------        bool for confirm
            password_bool=false;
        }


    }
}
//Global varibles of ticket & admin


//---------------------------------------------     user class  -------------------------------
class user implements ActionListener{
    //---now will make a user class for the confirming of the ticket
    JFrame frame;
    String hello;
    Ticket_info info,temperay;
    Font f=new Font("Algerian",Font.BOLD,40);
    JButton back,next;
    user(){
        frame=new JFrame();
        frame.setLayout(null);
        frame.setSize(2000,2000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon=new ImageIcon("flag.png");
        JLabel img=new JLabel(icon);
        ImageIcon i=new ImageIcon("p.png");
        JLabel im=new JLabel(i);
        im.setBounds(1000,0,500,500);
        frame.add(im);

        img.setBounds(0,0,1000,500);
        frame.add(img);

        JLabel write=new JLabel("Tackling the foreign affairs");
        write.setBounds(500, 540,800,40);
        write.setFont(f);
        frame.add(write);

        //------------------------  buttons -----------------
        back=new JButton("<- Back");
        back.setFocusable(false);
        back.setBounds(400,600,200,60);
        back.setFont(f);
        back.addActionListener(this);
        frame.add(back);

        next=new JButton("next ->");
        next.setFocusable(false);
        next.setBounds(700,600,200,60);
        next.setFont(f);
        next.addActionListener(this);
        frame.add(next);

        frame.getContentPane().setBackground(Color.gray);
        frame.setVisible(true);


    }
    user(String a,Ticket_info b){
        temperay=b;
        info=b;
        hello=a;
        frame=new JFrame();
        frame.setLayout(null);
        frame.setSize(2000,2000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon=new ImageIcon("flag.png");
        JLabel img=new JLabel(icon);
        ImageIcon i=new ImageIcon("p.png");
        JLabel im=new JLabel(i);
        im.setBounds(1000,0,500,500);
        frame.add(im);

        img.setBounds(0,0,1000,500);
        frame.add(img);

        JLabel write=new JLabel("Tackling the foreign affairs");
        write.setBounds(500, 540,800,40);
        write.setFont(f);
        frame.add(write);

        //------------------------  buttons -----------------
        back=new JButton("<- Back");
        back.setFocusable(false);
        back.setBounds(400,600,200,60);
        back.setFont(f);
        back.addActionListener(this);
        frame.add(back);

        next=new JButton("next ->");
        next.setFocusable(false);
        next.setBounds(700,600,200,60);
        next.setFont(f);
        next.addActionListener(this);
        frame.add(next);

        frame.getContentPane().setBackground(Color.gray);
        frame.setVisible(true);




    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==back){
            new LoginPortal(hello,info);
            frame.setVisible(false);
            frame.dispose();
        }
        if(e.getSource()==next){
            info.setVisible(true);
            frame.setVisible(false);
            new LoginPortal(hello,info);
        }
    }
}

//---   ------------------------------------------------------      registeration class
class reg implements ActionListener {
    private JFrame frame;
    private JTextField emailField,f_name,l_name;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton back;
    private JLabel first_name,last_name,gender;
    private JTextField write_first,write_last;
    private JRadioButton op1,op2,op3;
    String email,password,name;
    Font f=new Font("Arial",Font.BOLD,30);
    Font f2=new Font("Castellar",Font.BOLD,34);
    String p;
    Ticket_info ticket;
    JLabel certain_issue;

    public reg() {
        frame = new JFrame("Login Page");
        frame.setLayout(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);


        //names
        first_name = new JLabel("First name:");
        first_name.setBounds(150, 150, 250, 40);
        first_name.setFont(f);
        frame.add(first_name);


        //----------------------><gender><--------------------------
        gender = new JLabel("Gender:");
        gender.setBounds(150, 280, 150, 40);
        gender.setFont(f);
        frame.add(gender);


        //ButtonGroup buttonGroup = new ButtonGroup();
        //
        //        // Create option buttons
        //        JRadioButton option1 = new JRadioButton("Option 1");
        //        JRadioButton option2 = new JRadioButton("Option 2");
        //        JRadioButton option3 = new JRadioButton("Option 3");
        //
        //        // Add option buttons to the button group
        //        buttonGroup.add(option1);
        //        buttonGroup.add(option2);
        //        buttonGroup.add(option3);
        //
        //        // Add option buttons to the frame
        //        frame.add(option1);
        //        frame.add(option2);
        //        frame.add(option3);

        ButtonGroup buttonGroup = new ButtonGroup();
        op1=new JRadioButton("Male");
        op1.setBounds(150,320,100,20);


        op2=new JRadioButton("Female");
        op2.setBounds(150,350,100,20);


        op3=new JRadioButton("custom");
        op3.setBounds(150,380,100,20);

        buttonGroup.add(op1);
        buttonGroup.add(op2);
        buttonGroup.add(op3);
        frame.add(op1);
        frame.add(op2);
        frame.add(op3);



        // Email field
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(150, 450, 100, 40);
        emailLabel.setFont(f);
        frame.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(400, 450, 200, 40);
        frame.add(emailField);

        // Password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(150, 500, 200, 40);
        passwordLabel.setFont(f);
        frame.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(400, 500, 200, 40);
        frame.add(passwordField);

        // First name field
        JLabel firstNameLabel = new JLabel("First Name:");

        firstNameLabel.setBounds(150, 150, 200, 40);
        firstNameLabel.setFont(f);
        frame.add(firstNameLabel);

        //first name textfield.....
        f_name = new JTextField();
        f_name.setBounds(400, 150, 200, 40);
        frame.add(f_name);

        loginButton = new JButton("Login");
        loginButton.setBounds(400, 700, 100, 40);
        frame.add(loginButton);
        loginButton.addActionListener(this);

        // Back button
        back = new JButton("Back");
        back.setBounds(250, 700, 100, 40);
        back.addActionListener(this);
        frame.add(back);

        ImageIcon icon = new ImageIcon("lock.png");
        JLabel label = new JLabel(icon);
        label.setBounds(700, -350, 750, 1700);
        frame.add(label);

        frame.getContentPane().setBackground(Color.BLACK);
        frame.setVisible(true);
    }
    public reg(String pass,Ticket_info t) {
        p=pass;
        ticket=t;
        frame = new JFrame("Login Page");
        frame.setLayout(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);


        //names
        first_name = new JLabel("First name:");
        first_name.setBounds(150, 150, 250, 40);
        first_name.setFont(f);
        frame.add(first_name);


        //----------------------><gender><--------------------------
        gender = new JLabel("Gender:");
        gender.setBounds(150, 280, 150, 40);
        gender.setFont(f);
        frame.add(gender);


        //ButtonGroup buttonGroup = new ButtonGroup();
        //
        //        // Create option buttons
        //        JRadioButton option1 = new JRadioButton("Option 1");
        //        JRadioButton option2 = new JRadioButton("Option 2");
        //        JRadioButton option3 = new JRadioButton("Option 3");
        //
        //        // Add option buttons to the button group
        //        buttonGroup.add(option1);
        //        buttonGroup.add(option2);
        //        buttonGroup.add(option3);
        //
        //        // Add option buttons to the frame
        //        frame.add(option1);
        //        frame.add(option2);
        //        frame.add(option3);

        ButtonGroup buttonGroup = new ButtonGroup();
        op1=new JRadioButton("Male");
        op1.setBounds(150,320,100,20);


        op2=new JRadioButton("Female");
        op2.setBounds(150,350,100,20);


        op3=new JRadioButton("custom");
        op3.setBounds(150,380,100,20);

        buttonGroup.add(op1);
        buttonGroup.add(op2);
        buttonGroup.add(op3);
        frame.add(op1);
        frame.add(op2);
        frame.add(op3);



        // Email field
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(150, 450, 100, 40);
        emailLabel.setFont(f);
        frame.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(400, 450, 200, 40);
        frame.add(emailField);

        // Password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(150, 500, 200, 40);
        passwordLabel.setFont(f);
        frame.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(400, 500, 200, 40);
        frame.add(passwordField);

        // First name field
        JLabel firstNameLabel = new JLabel("First Name:");

        firstNameLabel.setBounds(150, 150, 200, 40);
        firstNameLabel.setFont(f);
        frame.add(firstNameLabel);

        //first name textfield.....
        f_name = new JTextField();
        f_name.setBounds(400, 150, 200, 40);
        frame.add(f_name);

        loginButton = new JButton("Login");
        loginButton.setBounds(400, 700, 100, 40);
        frame.add(loginButton);
        loginButton.addActionListener(this);

        // Back button
        back = new JButton("Back");
        back.setBounds(250, 700, 100, 40);
        back.addActionListener(this);
        frame.add(back);

        ImageIcon icon = new ImageIcon("lock.png");
        JLabel label = new JLabel(icon);
        label.setBounds(700, -350, 750, 1700);
        frame.add(label);

        //certain_issue= new JLabel("The information filled is incomplete\uD83D\uDE28\uD83D\uDE28");
        certain_issue= new JLabel("\uD83D\uDE28The   information   filled   is   incomplete  !!!!");
        certain_issue.setBounds(0, 0, 0, 0);
        frame.add(certain_issue);
        certain_issue.setFont(f2);
        certain_issue.setForeground(Color.RED);

        frame.getContentPane().setBackground(Color.BLACK);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back) {
            new gui_startup();
            frame.dispose();
        }
        else if(e.getSource()==loginButton){
            name=f_name.getText();
            email=emailField.getText();
            password=passwordField.getText();
            if(!email.equals("") && !password.equals("")){
                certain_issue.setBounds(0,0,0,0);
                System.out.println("not empty");
                System.out.println("passowrd:"+password);
                System.out.println("email:"+email);
                //--------------------------        FILE--handling      --------------------------------

                try {
                    FileWriter fileWriter = new FileWriter("example.txt",true);
                    fileWriter.write(email+","+password+","+name+"\n");
                    fileWriter.close();
                }
                catch (IOException i){

                    System.out.println("");
                    i.printStackTrace();
                }

                //-----------       <-now close it->       -----------------

                frame.setVisible(false);
                new LoginPortal(p,ticket);

            }

            else if(email.equals("") && password.equals("")){
                certain_issue.setBounds(50,50,1100,100);
                System.out.println("empty");
            }
        }
    }
}
class LoginPortal extends Admin_password implements ActionListener {
    private JFrame frame;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton back;
    String pass;
    Ticket_info ticket;

    Font f=new Font("Arial",Font.BOLD,30);
    JLabel certain_issue;
    Font f2=new Font("Castellar",Font.BOLD,34);

    public LoginPortal() {
        frame = new JFrame("Login Page");
        frame.setLayout(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Email field
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(200, 200, 100, 40);
        emailLabel.setFont(f);
        frame.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(400, 200, 200, 40);
        frame.add(emailField);

        // Password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(200, 300, 200, 40);
        frame.add(passwordLabel);
        passwordLabel.setFont(f);

        passwordField = new JPasswordField();
        passwordField.setBounds(400, 300, 200, 40);
        frame.add(passwordField);

        // Login button
        loginButton = new JButton("Login");
        loginButton.setBounds(400, 400, 100, 40);
        loginButton.addActionListener(this);
        frame.add(loginButton);

        //back
        back = new JButton("Back");
        back.setBounds(250, 400, 100, 40);
        back.addActionListener(this);
        back.addActionListener(this);
        frame.add(back);

        ImageIcon icon=new ImageIcon("lock.png");
        JLabel label=new JLabel(icon);
        label.setBounds(700,-350,750,1700);

        frame.add(label);

        frame.getContentPane().setBackground(Color.BLACK);
        frame.setVisible(true);
    }

    public LoginPortal(String p,Ticket_info t) {

        pass=p;
        ticket=t;

        frame = new JFrame("Login Page");
        frame.setLayout(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Email field
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(200, 200, 100, 40);
        emailLabel.setFont(f);
        frame.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(400, 200, 200, 40);
        frame.add(emailField);

        // Password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(200, 300, 200, 40);
        frame.add(passwordLabel);
        passwordLabel.setFont(f);

        passwordField = new JPasswordField();
        passwordField.setBounds(400, 300, 200, 40);
        frame.add(passwordField);

        // Login button
        loginButton = new JButton("Login");
        loginButton.setBounds(400, 400, 100, 40);
        loginButton.addActionListener(this);
        frame.add(loginButton);

        //back
        back = new JButton("Back");
        back.setBounds(250, 400, 100, 40);
        back.addActionListener(this);
        back.addActionListener(this);
        frame.add(back);

        ImageIcon icon=new ImageIcon("lock.png");
        JLabel label=new JLabel(icon);
        label.setBounds(700,-350,750,1700);

        frame.add(label);

        frame.getContentPane().setBackground(Color.BLACK);

        //issue--------------------------------------------------------------
        certain_issue= new JLabel("\uD83D\uDE28The   information   filled   is   incomplete  !!!!");
        certain_issue.setBounds(0, 0, 0, 0);
        frame.add(certain_issue);
        certain_issue.setFont(f2);
        certain_issue.setForeground(Color.RED);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==back){
            new gui_startup(pass,ticket);
            frame.dispose();
        }

        else if(e.getSource()==loginButton){
            String get_email=emailField.getText();
            String get_password=passwordField.getText();

            System.out.println("user-email"+email);
            System.out.println(get_email);
            System.out.println("admin-stored"+pass);
            System.out.println("user-password"+password);
            if (email.equals(get_email)&& pass.equals(get_password)){
                new Admin(pass,ticket);
                frame.dispose();
            }
            else{

                if(get_email.equals("")|| get_password.equals("")){
                    certain_issue.setBounds(50,50,1100,100);
                    System.out.println("empty");
                }
                else {
                    certain_issue.setBounds(0,0,0,0);
                    new file_handling("example.txt", get_email, get_password, pass, ticket);
                    frame.dispose();
                }

            }
        }

    }
}
//---------            ---                                                             -person---------------------------
class person{
    protected String first_name;
    protected String last_name;
    protected String password;
    protected Date d;
    person(){
        d=new Date();
    }
    person(String f_name,String l_name,String p){
        first_name=f_name;
        last_name=l_name;
        password=p;
        d=new Date();
    }
    void print_person(){
        System.out.println("first_name: "+first_name);
        System.out.println("last_name:  "+last_name);
        System.out.println("password:  "+password);
        System.out.println("date: "+d);
    }
}
class gui_startup extends JFrame implements ActionListener{

    JFrame frame;
    JTextField main_field,main_field2;
    JButton login,reg;
    String pass;
    Ticket_info ticket;
    String p="123";
    gui_startup(){
        //------------------------------        MAIN PORTAL SIZE        --------------------------------

        this.setSize(500,500);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //frame.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);



        //------------------------------        Text fields        ----------------------------------------
        main_field=new JTextField("Welcome to Pakistan      !!!!");
        main_field2=new JTextField("Register now->");

        main_field.setBounds(100,100,650,100);
        main_field.setEnabled(false);
        Font font=new Font("Arial",Font.BOLD,36);
        main_field.setFont(font);
        main_field.setBackground(Color.GRAY);
        this.add(main_field);



        //------------------------------        Panel for the edges       --------------------------------
        JPanel Panel=new JPanel();
        JPanel Panel1=new JPanel();
        JPanel Panel2=new JPanel();
        JPanel Panel3=new JPanel();
        JPanel Panel4=new JPanel();

        Panel.setBackground(Color.DARK_GRAY);
        Panel1.setBackground(Color.DARK_GRAY);
        Panel2.setBackground(Color.DARK_GRAY);
        Panel3.setBackground(Color.DARK_GRAY);
        Panel4.setBackground(Color.DARK_GRAY);

        Panel1.setBounds(100,0,1500,100);
        Panel.setBounds(0,0,100,900);
        Panel2.setBounds(750,0,100,900);
        Panel3.setBounds(0,750,1900,80);
        Panel4.setBounds(1460,0,100,1000);


        this.add(Panel1);
        this.add(Panel);
        this.add(Panel2);
        this.add(Panel3);
        this.add(Panel4);

        //-----------------------------         Image icon       ---------------------------------------------
        ImageIcon img=new ImageIcon("wel.png");
        ImageIcon img2=new ImageIcon("win2.png");

        JLabel label=new JLabel(img);
        JLabel label1=new JLabel(img2);

        label1.setBounds(850,-10,620,680);
        label.setBounds(100,100,650,660);

        this.add(label1);
        this.add(label);


        //-----------------------------         Login       ---------------------------------------------
        login=new JButton("Login");
        login.setFocusable(false);
        login.addActionListener(this);
        login.setBounds(1000,600,100,50);


        JPasswordField pas=new JPasswordField();
        //-----------------------------         reg       ---------------------------------------------
        reg=new JButton("register");
        reg.setFocusable(false);
        reg.setBounds(1200,600,120,50);
        reg.addActionListener(this);

        this.add(login);
        this.add(reg);
        this.setVisible(true);
    }

    //-------------------------------------------------------------------       new constructor     ----------------------------

    gui_startup(String a,Ticket_info x){
        //------------------------------        MAIN PORTAL SIZE        --------------------------------

        ticket=x;

        p = a;


        this.setSize(500,500);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //frame.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);



        //------------------------------        Text fields        ----------------------------------------
        main_field=new JTextField("Welcome to Pakistan      !!!!");
        main_field2=new JTextField("Register now->");

        main_field.setBounds(100,100,650,100);
        main_field.setEnabled(false);
        Font font=new Font("Arial",Font.BOLD,36);
        main_field.setFont(font);
        main_field.setBackground(Color.GRAY);
        this.add(main_field);



        //------------------------------        Panel for the edges       --------------------------------
        JPanel Panel=new JPanel();
        JPanel Panel1=new JPanel();
        JPanel Panel2=new JPanel();
        JPanel Panel3=new JPanel();
        JPanel Panel4=new JPanel();

        Panel.setBackground(Color.DARK_GRAY);
        Panel1.setBackground(Color.DARK_GRAY);
        Panel2.setBackground(Color.DARK_GRAY);
        Panel3.setBackground(Color.DARK_GRAY);
        Panel4.setBackground(Color.DARK_GRAY);

        Panel1.setBounds(100,0,1500,100);
        Panel.setBounds(0,0,100,900);
        Panel2.setBounds(750,0,100,900);
        Panel3.setBounds(0,750,1900,80);
        Panel4.setBounds(1460,0,100,1000);


        this.add(Panel1);
        this.add(Panel);
        this.add(Panel2);
        this.add(Panel3);
        this.add(Panel4);

        //-----------------------------         Image icon       ---------------------------------------------
        ImageIcon img=new ImageIcon("wel.png");
        ImageIcon img2=new ImageIcon("win2.png");

        JLabel label=new JLabel(img);
        JLabel label1=new JLabel(img2);

        label1.setBounds(850,-10,620,680);
        label.setBounds(100,100,650,660);

        this.add(label1);
        this.add(label);


        //-----------------------------         Login       ---------------------------------------------
        login=new JButton("Login");
        login.setFocusable(false);
        login.addActionListener(this);
        login.setBounds(1000,600,100,50);


        JPasswordField pas=new JPasswordField();
        //-----------------------------         reg       ---------------------------------------------
        reg=new JButton("register");
        reg.setFocusable(false);
        reg.setBounds(1200,600,120,50);
        reg.addActionListener(this);



        this.add(login);
        this.add(reg);
        this.setVisible(true);
    }






    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==login){
            new LoginPortal(p,ticket);
            this.dispose();
        }
        if(e.getSource()==reg){
            new reg(p,ticket);
            this.dispose();
        }

    }
}
class project {
    public static void main(String[] args) {
//        Ticket_info a=new Ticket_info();
//        a.setVisible(true);
////
//
//        Admin password=new Admin(ticket);
//        ticket.setVisible(true);\\
        Ticket_info a= new Ticket_info();
        new  gui_startup("123",a);

    }
}

