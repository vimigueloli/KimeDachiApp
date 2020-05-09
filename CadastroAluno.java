//imports
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


//classe do tipo tela
public class CadastroAluno extends JFrame implements ActionListener, ItemListener{


      //variaveis privadas 
      private Container painelDeConteudo;
      private JTextField nome= new JTextField("");
      private JTextField ra= new JTextField("");
      private JTextField email= new JTextField("");
      private JLabel nomeA= new JLabel("Nome:");
      private JLabel raA= new JLabel("RA:");
      private JLabel emailA= new JLabel("E-mail:");
      private JLabel turmaA= new JLabel("turma:");
      private JButton adicionar= new JButton("adicionar");
      private JButton voltar= new JButton("voltar");
      private ImageIcon i= new ImageIcon("C:/Users/casa oliveira/Documents/PI/kimedachi.png");
      private JLabel kimedachi=new JLabel(i);
      
      //relacionados a tabela
      private JScrollPane container;            
      private JTable tabela;
      private String[][] conteudo;     
      private String colunas[]= {"nome","RA"};

      //relacionados a combo box
      private JComboBox filtro;
      private String[] vet;  
      private String alunos="ECP3BN";
      
      //relacionados ao tamanho da tela
      private int telaW;
      private int telaH;
      private int centroW;
      private int centroH;
      
      //relacionados a interatividade
      private String n,r,e,t;
      

      //classe que implementa tudo      
      public CadastroAluno(){
         
         super("Alunos");
         
         //declara valor ao painel   
         painelDeConteudo= getContentPane(); 
         
            
         //tamanho e centro da tela   
         Toolkit tk = Toolkit.getDefaultToolkit();
         Dimension d = tk.getScreenSize();
         telaW= d.width;
         telaH= d.height;
         centroW = telaW/2;
         centroH = telaH/2;
         

         //coloca os itens na combo box        
         vet = indicaTurmas();   
         filtro = new JComboBox(vet);
            
         //seleciona oq ta dentro da combo box
         alunos = (String) filtro.getSelectedItem();

         
         //instancia tabela
         conteudo= indicaAlunos();
         container = criaTabela();
         



         
         //painel geral
         painelDeConteudo.setLayout(null); 
         
         
         //posicionamento das coisas   
         
         kimedachi.setBounds(centroW-50,centroH-375,100,100);
         nomeA.setBounds(centroW-20,centroH-235,40,10);
         nome.setBounds(centroW-150,centroH-220,300,20);
         raA.setBounds(centroW-10,centroH-195,30,12);
         ra.setBounds(centroW-150,centroH-180,300,20);
         emailA.setBounds(centroW-20,centroH-155,60,12);
         email.setBounds(centroW-150,centroH-140,300,20);
         turmaA.setBounds(centroW-20,centroH-115,60,12);
         filtro.setBounds(centroW-150,centroH-100,300,19);
         adicionar.setBounds(centroW-150,centroH-60,300,20);
         container.setBounds(centroW-300,centroH,600,200); 
         voltar.setBounds(centroW-150,centroH+240,300,20);
         
         
         //listener nos bot�es   
         adicionar.addActionListener(this);        
         voltar.addActionListener(this);         
         
         //listener da combo box   
         filtro.addItemListener(this);
         
               
         
         //adiciona as coisas na tela 
         painelDeConteudo.add(kimedachi);
         painelDeConteudo.add(turmaA);
         painelDeConteudo.add(filtro);
         painelDeConteudo.add(nomeA);
         painelDeConteudo.add(nome);
         painelDeConteudo.add(raA);
         painelDeConteudo.add(ra);
         painelDeConteudo.add(emailA);
         painelDeConteudo.add(email);
         painelDeConteudo.add(adicionar);         
         painelDeConteudo.add(container);
         painelDeConteudo.add(voltar);
            
         
         //arruma tamanho layout e visibilidade do frame
         setSize(telaW,telaH);
         setExtendedState(JFrame.MAXIMIZED_BOTH);//tela cheia
         setLayout(null);
         setVisible(true);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//fecha a aplicacao
      }
      
      
      

      //metodo gera matriz tabela
      public String[][] indicaAlunos(){
         
         ListaAlunos lista =new ListaAlunos();
         int c= lista.contarAlunos(alunos);
         
         String[][] dados;
         dados =  new String [c][2];
         lista.listarAlunos(alunos,dados);
         
         return dados;
      
      }
   
   
      // cria uma tabela
      public JScrollPane criaTabela(){
         
         tabela= new JTable(conteudo,colunas);
 

         JScrollPane s = new JScrollPane(tabela);
         return s;  
      }
     


      //cria array da combo box
      public String[] indicaTurmas(){
      
         ListaTurmas lista =new ListaTurmas();
         int c= lista.contarTurma();
      
         String[] dados;
         dados =  new String [c];
         lista.listarTurma(dados);
         
         return dados;
      
      }
      
      
      
      //func�o dos bot�es
      public void actionPerformed(ActionEvent c){
         if(c.getSource() == adicionar){
            
            t=alunos;
            n=nome.getText();
            r=ra.getText();
            e=email.getText();
            
            Aluno aluno= new Aluno("","","","");
            aluno.setNomeA(n);
            aluno.setMatriculaA(r);
            aluno.setEmailA(e);
            aluno.setTurmaA(t);
            
            ListaAlunos adicionando= new ListaAlunos();
            adicionando.addAluno(aluno);
            
            alunos = (String) filtro.getSelectedItem();
            conteudo= indicaAlunos();
             
            remove(container);
            conteudo= indicaAlunos();            
            container = criaTabela();           
            add(container);           
            container.setBounds(centroW-300,centroH,600,200);             
            repaint();  
            
         }
      }
      
      
      
      
      //quando a tabela � selecionada
      

      
      //quando muda o item da combo box
      public void itemStateChanged(ItemEvent e){
         if(e.getStateChange() == ItemEvent.SELECTED){
          
            alunos = (String) filtro.getSelectedItem();
            conteudo= indicaAlunos();
            remove(container);
            container = criaTabela();
            add(container);
            container.setBounds(centroW-300,centroH,600,200); 
            repaint();
              
         }
      }
   
   

   //faz a tela aparecer
   public static void main (String [] args){
      SwingUtilities.invokeLater (new Runnable (){
         public void run (){
            new CadastroAluno();
         }
      });
   }
   

}