import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;


import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.sql.Connection;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.text.ParseException;





//cria a classe com indicação de JFrame e ActionListener
public class PadraoTelas extends JFrame implements ActionListener{


      private Container painelDeConteudo;   //tem que criar container

      //inicia os privates Jtable, Jlabel, JTextField, Jbutton      
      private JLabel nomeA= new JLabel("Nome:");
      private JTextField nome= new JTextField("");
      private JTextField data= new JTextField("");
      private JTextField presenca= new JTextField("");
      private JTextField nota= new JTextField("");
      private JLabel dataA= new JLabel("data:");
      private JLabel presencaA= new JLabel("presença:");
      private JLabel notaA= new JLabel("nota:");
      private JButton alterar= new JButton("alterar");;
      private JButton voltar= new JButton("voltar");;
      private JButton excluir= new JButton("excluir");;
      private ImageIcon i= new ImageIcon("C:/Users/casa oliveira/Documents/PI/kimedachi.png");//aqui coloca a URL da imagem
      private JLabel kimedachi=new JLabel(i);//coloca a imagem em uma label


   public PadraoTelas(){
   
   
         super ("nome da tela");
         
         //declara o painel         
         painelDeConteudo= getContentPane(); 
         
         //define o tamanho da tela
         Toolkit tk = Toolkit.getDefaultToolkit();
         Dimension d = tk.getScreenSize();
         int telaW= d.width;
         int telaH= d.height;
         int centroW = telaW/2;
         int centroH = telaH/2;
         

         //define a posição e tamanho dos itens (posição horizontal,posição vertical,tamanho horizontal, tamanho vertical)
         kimedachi.setBounds(centroW-50,centroH-375,100,100);
         nomeA.setBounds(centroW-20,centroH-235,40,10);
         nome.setBounds(centroW-150,centroH-220,300,20);
         dataA.setBounds(centroW-17,centroH-195,30,12);
         data.setBounds(centroW-150,centroH-180,300,20);
         presencaA.setBounds(centroW-30,centroH-155,60,12);
         presenca.setBounds(centroW-150,centroH-140,300,20);
         notaA.setBounds(centroW-17,centroH-115,30,10);
         nota.setBounds(centroW-150,centroH-100,300,20);
         alterar.setBounds(centroW-150,centroH-60,150,20);
         excluir.setBounds(centroW,centroH-60,150,20);
         voltar.setBounds(centroW-150,centroH+240,300,20);
         
         
         
         //coloca os botões pra fazerem algo quando apertados
         alterar.addActionListener(this);       
         voltar.addActionListener(this);
         excluir.addActionListener(this);
         
         
         
      
         
         //seta o estilo como nulo
         painelDeConteudo.setLayout(null); 
         
         
                  
         
         //adiciona as coisas na tela 
         painelDeConteudo.add(kimedachi);
         painelDeConteudo.add(nomeA);
         painelDeConteudo.add(nome);
         painelDeConteudo.add(dataA);
         painelDeConteudo.add(data);
         painelDeConteudo.add(notaA);
         painelDeConteudo.add(nota);
         painelDeConteudo.add(presencaA);
         painelDeConteudo.add(presenca);
         painelDeConteudo.add(alterar);
         painelDeConteudo.add(excluir);
         painelDeConteudo.add(voltar);
         
         
         
         //arruma tamanho layout e visibilidade do frame
         setSize(telaW,telaH);//tela do tamanho da tela do usuario
         setExtendedState(JFrame.MAXIMIZED_BOTH);//tela cheia
         setLayout(null);
         setVisible(true);//visivel    
         
      }
      
      
      
      //define oq os botões fazem 
      public void actionPerformed(ActionEvent e){
         if(e.getSource() == alterar){
            System.out.println("teste");
         }else if(e.getSource() == voltar){
            System.out.println("teste");
         }else if(e.getSource() == excluir){
            System.out.println("teste");
         }
      }


      //main para poder ver a tela 
      public static void main (String [] args){
         SwingUtilities.invokeLater (new Runnable (){
            public void run (){
               new PadraoTelas();//coloca o nome do arquivo no lugar de PadraoTelas
            }
         });
      }



}