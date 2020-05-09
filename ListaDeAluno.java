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









public class ListaDeAluno extends JFrame implements ActionListener, ItemListener, ListSelectionListener{


      //variaveis privadas
      
      private Container painelDeConteudo;     
      private JLabel nomeA= new JLabel("Nome:");
      private JTextField nome= new JTextField("");
      private JTextField data= new JTextField("");
      private JTextField presenca= new JTextField("");
      private JTextField nota= new JTextField("");
      private JLabel dataA= new JLabel("data:");
      private JLabel presencaA= new JLabel("presença:");
      private JLabel notaA= new JLabel("nota:");
      private JButton alterar= new JButton("alterar");
      private JButton voltar= new JButton("voltar");
      private JButton conferir= new JButton("conferir");
      private ImageIcon i= new ImageIcon("C:/Users/casa oliveira/Documents/PI/kimedachi.png");
      private JLabel kimedachi=new JLabel(i);
      private JComboBox filtro;
      
      private JScrollPane container;
      private JTable tabela1; 
      private JScrollPane container1;
      private String[][] conteudo;
      private String[][] conteudo1;      
      
      private String colunas[]= {"nome","RA"};
      private String colunasA[]= {"nota","data","presença"};
      
      private String[][] dadosA;
      private JTable tabela;
      
      private String[] vet; 
      
      private String alunos="ECP3BN"; 
      private boolean tela= true;
      private int telaW;
      private int telaH;

      private int centroW;
      private int centroH;
      
      private ListSelectionModel listSelectionModel;
                 
      
      private String ra;
      private String n,d,p;
      
      
      
      

      public ListaDeAluno(){
         
         

         painelDeConteudo= getContentPane(); 
         
         Toolkit tk = Toolkit.getDefaultToolkit();
         Dimension d = tk.getScreenSize();
         telaW= d.width;
         telaH= d.height;

         centroW = telaW/2;
         centroH = telaH/2;
         
      
         
                 
         vet = indicaTurmas();
         
                   

         filtro = new JComboBox(vet);
         
         alunos = (String) filtro.getSelectedItem();
         
         
         
         
         
         conteudo= indicaAlunos();
         container = criaTabela();
         
         
         
         
         
          
         

         
         
         

         
         conteudo1= tamanhoNotas();
         container1 = criaTabela1();
         
         
         
         
         
         painelDeConteudo.setLayout(null); 
         
         

         filtro.setBounds(centroW-150,centroH-265,300,19);
         kimedachi.setBounds(centroW-50,centroH-375,100,100);
         nomeA.setBounds(centroW-20,centroH-235,40,10);
         nome.setBounds(centroW-150,centroH-220,300,20);
         dataA.setBounds(centroW-17,centroH-195,30,12);
         data.setBounds(centroW-150,centroH-180,300,20);
         presencaA.setBounds(centroW-30,centroH-155,60,12);
         presenca.setBounds(centroW-150,centroH-140,300,20);
         notaA.setBounds(centroW-17,centroH-115,30,10);
         nota.setBounds(centroW-150,centroH-100,300,20);
         conferir.setBounds(centroW-150,centroH-60,150,20);
         alterar.setBounds(centroW,centroH-60,150,20);
         container.setBounds(centroW-310,centroH,300,200);
         
         container1.setBounds(centroW+10,centroH,300,200);
         voltar.setBounds(centroW-150,centroH+240,300,20);
         
         
   
         alterar.addActionListener(this);        
         voltar.addActionListener(this);         
         conferir.addActionListener(this);
         
         filtro.addItemListener(this);
         
        
         
         
      
         
         
         
         
               
         
         //adiciona as coisas na tela 
         painelDeConteudo.add(kimedachi);
         painelDeConteudo.add(filtro);
         painelDeConteudo.add(nomeA);
         painelDeConteudo.add(nome);
         painelDeConteudo.add(dataA);
         painelDeConteudo.add(data);
         painelDeConteudo.add(notaA);
         painelDeConteudo.add(nota);
         painelDeConteudo.add(presencaA);
         painelDeConteudo.add(presenca);
         painelDeConteudo.add(alterar);         
         painelDeConteudo.add(conferir);
         painelDeConteudo.add(container);
         painelDeConteudo.add(container1);
         painelDeConteudo.add(voltar);
         
         //arruma tamanho layout e visibilidade do frame
         setSize(telaW,telaH);
         setExtendedState(JFrame.MAXIMIZED_BOTH);//tela cheia
         setLayout(null);
         setVisible(true);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//fecha a aplicacao
      }
      
      
      
      
      
      /*public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.out.println("tela anterior");
      }
      
      // faz os botões fazerem algo
      private void alteraCadastro(){
          System.out.println("faz oq o botão tem que fazer");
      }
      public void excluiCadastro(ActionEvent e){
          System.out.println("faz oq o botão tem que fazer");
      }*/
      
      
      
        
      //ativa toda vez que escolhem algo da combo box
      /*public void itemStateChanged(ItemEvent e){
      
         if(e.getStateChange() == ItemEvent.SELECTED){
            conteudo = carregaDados();
            //o valor da tabela é atualizado
            
            
            //e agora temos que criar um codigo que muda a tela
            refazTela();
            //no final do codigo eu crio esse metodo e explico como funciona
            
         }
       
      }*/
      
      
      
      
      
      //então aqui temos que criar uma matriz de string 
      //usando esse metodo grantimos que a tabela vai estar atualizada com o banco
      public String[][] indicaAlunos(){
         
         ListaAlunos lista =new ListaAlunos();
         int c= lista.contarAlunos(alunos);
         
         String[][] dados;
         dados =  new String [c][2];
         lista.listarAlunos(alunos,dados);
         
         return dados;
      
      }//pode voltar para onde tinhamos parado


      public String[][] tamanhoNotas(){
         
         ListaNotas lista =new ListaNotas();
         int c= lista.contarNotas(ra);
         String[][]tam;

         tam =  new String [c][3];
         lista.listarNotas(ra,tam);
         
         return tam;
      
      }
   
   
      // então primeiro de tudo temos que ter em mente que pra isso funcionar ja devem ter sido criados uma JTable,
      //um vetor com o nome das colunas, e uma matriz de objetos 
      // a parada desse metodo é q ele vai permitir atualizarmos a tabela
      public JScrollPane criaTabela(){
      
         
         tabela= new JTable(conteudo,colunas);
         tabela.setCellSelectionEnabled(true); 
         listSelectionModel = tabela.getSelectionModel();
         listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
         listSelectionModel.addListSelectionListener(this);
         tabela.setSelectionModel(listSelectionModel);
         //ta.getSelectionModel().addListSelectionListener(this);
            
         //a JTable é colocada dentro de um Scrollpane
         JScrollPane s = new JScrollPane(tabela);
         return s;  //pode voltar lá pra onde tinhamos parado
      }
      
      public JScrollPane criaTabela1(){
      
         //a JTable recebe a matriz objeto e o vetor com o nome das colunas
         tabela1 = new JTable(conteudo1, colunasA);
         
         listSelectionModel = tabela1.getSelectionModel();
         listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
         listSelectionModel.addListSelectionListener(this);
         tabela1.setSelectionModel(listSelectionModel);

            
         //a JTable é colocada dentro de um Scrollpane
         JScrollPane s = new JScrollPane(tabela1);
         return s;  //pode voltar lá pra onde tinhamos parado
      }

   
   
   
      
      
      
      
      
      public String[] indicaTurmas(){
      
         ListaTurmas lista =new ListaTurmas();
         int c= lista.contarTurma();
      
         String[] dados;
         dados =  new String [c];
         lista.listarTurma(dados);
         
         return dados;
      
      }
      
      
      
      
      public void actionPerformed(ActionEvent c){
         if(c.getSource() == alterar){
            
            Nota notaO= new Nota(0,"","");
            Nota notaN= new Nota(0,"","");
            
            notaO.setValor(Double.parseDouble(n));
            notaO.setData(d);
            notaO.setPresenca(p);
            
            double nN= Double.parseDouble(nota.getText());
            String dN= data.getText();
            String pN= presenca.getText();         
            
            notaN.setValor(nN); 
            notaN.setData(dN);
            notaN.setPresenca(pN);
            
            ListaNotas alterando= new ListaNotas();
            alterando.alterarNota(notaO,ra,notaN);
         
         
            remove(container1);
            conteudo1= tamanhoNotas();            
            container1 = criaTabela1();           
            add(container1);           
            container1.setBounds(centroW+10,centroH,300,200);            
            repaint();  
            
         }else if(c.getSource() == conferir){            
            remove(container1);
            conteudo1= tamanhoNotas();            
            container1 = criaTabela1();           
            add(container1);           
            container1.setBounds(centroW+10,centroH,300,200);            
            repaint();
         }
      }
      
      
      
      public void valueChanged(ListSelectionEvent eve)
    {
      if(eve.getValueIsAdjusting()){
         
            String s = null;  
            System.out.println("debug:" + tabela );
            //int[] row = tabela.getSelectedRows();  
            //int[] columns = tabela.getSelectedColumns();  
            s = ""+ tabela.getValueAt(tabela.getSelectedRow(),0);
            ra = ""+ tabela.getValueAt(tabela.getSelectedRow(),1);
            nome.setText(s);  
            
            
            n= ""+ tabela1.getValueAt(tabela1.getSelectedRow(),0);
            d= ""+ tabela1.getValueAt(tabela1.getSelectedRow(),1);
            p= ""+ tabela1.getValueAt(tabela1.getSelectedRow(),2);
            
            data.setText(d);
            nota.setText(n);
            presenca.setText(p);
                 
            
      }
    }
    

      
      
      public void itemStateChanged(ItemEvent e){
         //sempre que muda o id selecionado na combobox este evento e gerado
         if(e.getStateChange() == ItemEvent.SELECTED){
            //carrega a matriz de pedidos para instanciar a JTable
            
            alunos = (String) filtro.getSelectedItem();
            System.out.println("oi");
          
            alunos = (String) filtro.getSelectedItem();
            

            conteudo= indicaAlunos();
            remove(container);
            container = criaTabela();
            
            add(container);
            
            
            container.setBounds(centroW-310,centroH,300,200);
            
            repaint();
            
            
         }
      }
   
   

   
   public static void main (String [] args){
      SwingUtilities.invokeLater (new Runnable (){
         public void run (){
            new ListaDeAluno();
         }
      });
   }
   



   
   
   
   
   
}    