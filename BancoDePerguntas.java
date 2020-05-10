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
public class BancoDePerguntas extends JFrame implements ActionListener, ListSelectionListener {


      //variaveis privadas 
      private Container painelDeConteudo;
      private JTextField perguntaT= new JTextField("");
      private JTextField respostaT= new JTextField("");
      private JTextField temaT= new JTextField("");
      private JTextField idT= new JTextField("pergunta em 2 palavras ou menos");
      private JLabel pergunta= new JLabel("pergunta:");
      private JLabel resposta= new JLabel("resposta:");
      private JLabel tema= new JLabel("tema:");
      private JLabel id= new JLabel("resumo:");
      private JButton alterar= new JButton("alterar");
      private JButton voltar= new JButton("voltar");
      private ImageIcon i= new ImageIcon("C:/Users/casa oliveira/Documents/PI/kimedachi.png");
      private JLabel kimedachi=new JLabel(i);
      
      //relacionados a tabela
      private JScrollPane container; 
      private JScrollPane pp;
      private JScrollPane rr;           
      private JTable tabela;
      private String[][] conteudo;     
      private String colunas[]= {"pergunta","tema"};
      
      
      //relacionados ao tamanho da tela
      private int telaW;
      private int telaH;
      private int centroW;
      private int centroH;
      
      //relacionados a interatividade
      private ListSelectionModel listSelectionModel;
      private String p,r,t,re;
      private String pN,rN,tN,reN;
      Pergunta perg= new Pergunta("","","","");
      Pergunta pergN= new Pergunta("","","","");

      //classe que implementa tudo      
      public BancoDePerguntas(){
         
         super("Perguntas");
         
         //declara valor ao painel   
         painelDeConteudo= getContentPane(); 
         
            
         //tamanho e centro da tela   
         Toolkit tk = Toolkit.getDefaultToolkit();
         Dimension d = tk.getScreenSize();
         telaW= d.width;
         telaH= d.height;
         centroW = telaW/2;
         centroH = telaH/2;
         

         
         //instancia tabela
         conteudo= indicaPerguntas();
         container = criaTabela();
         


         //painel geral
         painelDeConteudo.setLayout(null); 
         
         
         
         pp = new JScrollPane(perguntaT);
         rr = new JScrollPane(respostaT);

         
         
         //posicionamento das coisas   
         
         kimedachi.setBounds(centroW-50,centroH-375,100,100);
         pergunta.setBounds(centroW-33,centroH-235,66,15);
         pp.setBounds(centroW-300,centroH-220,600,60);
         resposta.setBounds(centroW-33,centroH-160,66,12);
         rr.setBounds(centroW-300,centroH-145,600,60);
         tema.setBounds(centroW+120,centroH-80,60,12);
         temaT.setBounds(centroW+5,centroH-65,295,19);
         id.setBounds(centroW-180,centroH-80,60,12);
         idT.setBounds(centroW-300,centroH-65,295,19);
         alterar.setBounds(centroW-150,centroH-35,300,20);
         container.setBounds(centroW-300,centroH,600,200); 
         voltar.setBounds(centroW-150,centroH+240,300,20);
         
         
         //listener nos botões   
         alterar.addActionListener(this);        
         voltar.addActionListener(this);         


               
         
         //adiciona as coisas na tela 
         painelDeConteudo.add(kimedachi);
         painelDeConteudo.add(id);
         painelDeConteudo.add(idT);
         painelDeConteudo.add(pergunta);
         painelDeConteudo.add(pp);
         painelDeConteudo.add(resposta);
         painelDeConteudo.add(rr);
         painelDeConteudo.add(tema);
         painelDeConteudo.add(temaT);
         painelDeConteudo.add(alterar);         
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
      public String[][] indicaPerguntas(){
         
         ListaPerguntas lista =new ListaPerguntas();
         int c= lista.contarPerguntas();
         
         String[][] dados;
         dados =  new String [c][2];
         lista.listarPerguntas(dados);
         
         return dados;
      
      }
   
   
      // cria uma tabela
      public JScrollPane criaTabela(){
         
         tabela= new JTable(conteudo,colunas);
         
         listSelectionModel = tabela.getSelectionModel();
         listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
         listSelectionModel.addListSelectionListener(this);
         tabela.setSelectionModel(listSelectionModel);
 

         JScrollPane s = new JScrollPane(tabela);
         return s;  
      }
     


      /*//cria array da combo box
      public String[] indicaTurmas(){
      
         ListaTurmas lista =new ListaTurmas();
         int c= lista.contarTurma();
      
         String[] dados;
         dados =  new String [c];
         lista.listarTurma(dados);
         
         return dados;
      
      }*/
      
      
      
      //funcão dos botões
      public void actionPerformed(ActionEvent c){
         if(c.getSource() == alterar){
            
            pergN.setTema(temaT.getText());
            pergN.setPergunta(perguntaT.getText());
            pergN.setResposta(respostaT.getText());
            pergN.setId(idT.getText());
            
            
            
            ListaPerguntas adicionando= new ListaPerguntas();
            adicionando.alterarPergunta(perg,pergN);
            
            
            
             
            remove(container);
            conteudo= indicaPerguntas();            
            container = criaTabela();           
            add(container);           
            container.setBounds(centroW-300,centroH,600,200);             
            repaint(); 
            
            perguntaT.setText("");
            respostaT.setText("");
            temaT.setText("");
            idT.setText("");
            
         }
      }
      
      
      
      public void valueChanged(ListSelectionEvent eve){
            if(eve.getValueIsAdjusting()){
            
            
            r = ""+ tabela.getValueAt(tabela.getSelectedRow(),0);
            t = ""+ tabela.getValueAt(tabela.getSelectedRow(),1);
            
            ListaPerguntas list= new ListaPerguntas();
            list.getPerguntas(perg,t,r);
            
            perguntaT.setText(perg.getPergunta());
            respostaT.setText(perg.getResposta());
            temaT.setText(perg.getTema());
            idT.setText(perg.getId());
            

            }
      }
      
      
      //quando a tabela é selecionada
      

      
      /*/quando muda o item da combo box
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
      }*/   
   

   //faz a tela aparecer
   public static void main (String [] args){
      SwingUtilities.invokeLater (new Runnable (){
         public void run (){
            new BancoDePerguntas();
         }
      });
   }
   

}