/*
 * NovoDesktop.java
 *
 * Created on 10/07/2017, 13:36:11
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhojava;

import cadastros.CadastroCliente;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;
import javax.swing.*;
import modelos.JCTaskPane;
import utilitario.JBlocoContainer;
import utilitario.JBlocoContainer2;
import utilitario.ModifiedFlowLayout;

/**
 *
 * @author
 */
public class Desktop extends JFrame {

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Desktop(String titulo) {
        super(titulo);
        iniciaComponentes();
    }

    public static void main(String[] args) {
        Desktop desktop = new Desktop("Sistema Pedidos");
        desktop.setVisible(true);
    }

    public void iniciaComponentes() {
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        setLayout(new BorderLayout());
        jcpFundo.setLayout(new BorderLayout());
        jcpCentral.setLayout(new BorderLayout());
        add(jcpFundo);
        jcpFundo.add(jcpCentral, BorderLayout.CENTER);
        jcpFundo.setOpaque(true);
        jcpFundo.setBackground(Color.lightGray);
        jcpFundo.repaint();
        blocoContainerCentral.setJCTaskPanes(iniciaTasksCentral());
        iniciaScrollsPanes();
        jcpCentral.add(scrollPaneCentral, BorderLayout.CENTER);
    }

    private void zerarTask() {
        taskPessoas = null;
        taskProduto = null;
        taskMovimentacoes = null;

        jcpPessoas = new JPanel();
        jcpMovimentacoes = new JPanel();
        jcpProdutos = new JPanel();
    }

    public void atualizarDesktop() {
        jcpCentral.removeAll();
        blocoContainerCentral = new JBlocoContainer2();
        zerarTask();
        blocoContainerCentral.setJCTaskPanes(iniciaTasksCentral());
        taskPessoas.setExpanded(false);

        scrollPaneCentral = new JScrollPane(blocoContainerCentral, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneCentral.setOpaque(false);
        ((JComponent) scrollPaneCentral.getComponent(0)).setOpaque(false);
        ((JComponent) scrollPaneCentral.getComponent(1)).setOpaque(false);
        scrollPaneCentral.getVerticalScrollBar().addAdjustmentListener(new java.awt.event.AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(java.awt.event.AdjustmentEvent e) {
                scrollPaneCentral.repaint();
            }
        });

        jcpCentral.add(scrollPaneCentral, BorderLayout.CENTER);
        jcpCentral.revalidate();
        jcpCentral.repaint();
    }

    private void iniciaScrollsPanes() {
        scrollPaneCentral = new JScrollPane(blocoContainerCentral, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneCentral.setOpaque(false);
        ((JComponent) scrollPaneCentral.getComponent(0)).setOpaque(false);
        ((JComponent) scrollPaneCentral.getComponent(1)).setOpaque(false);
        scrollPaneCentral.getVerticalScrollBar().addAdjustmentListener(new java.awt.event.AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(java.awt.event.AdjustmentEvent e) {
                scrollPaneCentral.repaint();
            }
        });

    }

    private Vector<JCTaskPane> iniciaTasksCentral() {
        Vector<JCTaskPane> tasksCentral = new Vector<JCTaskPane>();
        tasksCentral.add(getTaskMovimentacoes());
        tasksCentral.add(getTaskPessoas());
        tasksCentral.add(getTaskProdutos());
        for (JCTaskPane jCTaskPane : tasksCentral) {
            jCTaskPane.getJcbTitulo().setBackground(new java.awt.Color(0, 0, 0, 0));
            jCTaskPane.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 10));
        }
        return tasksCentral;
    }
    public Color corNormal = null;
    public Color corNormalSelected = null;

    public final ImageIcon getImageIcon(String caminhoImagem) {
        if (caminhoImagem.equals("")) {
            return null;
        } else {
            try {
                return new javax.swing.ImageIcon(getClass().getResource(caminhoImagem.replace("\\", "/")));
            } catch (NullPointerException e) {
                try {
                    return new javax.swing.ImageIcon(caminhoImagem.replace("\\", "/"));
                } catch (NullPointerException ex) {
                    return null;
                }
            }
        }
    }

    private JCTaskPane getTaskPessoas() {
        if (taskPessoas == null) {
            taskPessoas = new JCTaskPane("Clientes");
            taskPessoas.setImageIconEsquerdo("/imagem/icones/pessoa_cliente22.png");
            taskPessoas.setPreferredSize(new Dimension(300, 200));

            jspPessoas = new JScrollPane(jcpPessoas, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            jspPessoas.setHorizontalScrollBar(null);
            jspPessoas.getViewport().setOpaque(false);
            jspPessoas.setOpaque(false);

            jcpPessoas.setLayout(new ModifiedFlowLayout(ModifiedFlowLayout.LEFT));
            jcpPessoas.setAlignmentY(TOP_ALIGNMENT);
            jcpPessoas.setOpaque(false);

            JButton jcbClienteNovo = new JButton("Cadastrar Novo Cliente)", getImageIcon("/imagem/icones/pessoa_cliente2.png"));
            JButton jcbClienteManter = new JButton("Manutenção de Clientes", getImageIcon("/imagem/icones/pessoa_cliente.png"));
            jcpPessoas.add(jcbClienteNovo);
            jcpPessoas.add(jcbClienteManter);

            jcbClienteNovo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    exeCadastroCliente();
                }
            });
            jcbClienteManter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    exeManutencaoCliente();
                }
            });

        }
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                organizarBotoes(e, jcpPessoas);
            }
        });
        taskPessoas.add(jspPessoas, "Center");
        return taskPessoas;
    }

    private JCTaskPane getTaskProdutos() {
        if (taskProduto == null) {
            taskProduto = new JCTaskPane("Itens");
            taskProduto.setImageIconEsquerdo("/imagem/icones/produtos22.png");
            taskProduto.setPreferredSize(new Dimension(300, 330));

            jspProdutos = new JScrollPane(jcpProdutos, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            jspProdutos.setHorizontalScrollBar(null);
            jspProdutos.getViewport().setOpaque(false);
            jspProdutos.setOpaque(false);

            jcpProdutos.setLayout(new ModifiedFlowLayout(ModifiedFlowLayout.LEFT));
            jcpProdutos.setOpaque(false);

            JButton jcbProduto = new JButton("Cadastro de Itens", getImageIcon("/imagem/icones/produtos.png"));
            JButton jcbCorrecaoItens = new JButton("Correçãoo de Itens", getImageIcon("/imagem/icones/produtos-b.png"));

            jcpProdutos.add(jcbProduto);
            jcpProdutos.add(jcbCorrecaoItens);

            jcbProduto.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    exeCadastroProduto();
                }
            });
            jcbCorrecaoItens.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    exeManutencaoProduto();
                }
            });
        }

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                organizarBotoes(e, jcpProdutos);
            }
        });

        taskProduto.add(jspProdutos, "Center");
        return taskProduto;
    }

    private JCTaskPane getTaskMovimentacoes() {
        if (taskMovimentacoes == null) {
            taskMovimentacoes = new JCTaskPane("Movimentações");
            taskMovimentacoes.setImageIconEsquerdo("/imagem/icones/movimentacao.png");
            taskMovimentacoes.setPreferredSize(new Dimension(300, 330));

            jspMovimentacao = new JScrollPane(jcpMovimentacoes, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            jspMovimentacao.setHorizontalScrollBar(null);
            jspMovimentacao.getViewport().setOpaque(false);
            jspMovimentacao.setOpaque(false);

            jcpMovimentacoes.setLayout(new ModifiedFlowLayout(ModifiedFlowLayout.LEFT));
            jcpMovimentacoes.setOpaque(false);

        }

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {

                organizarBotoes(e, jcpMovimentacoes);
            }
        });

        taskMovimentacoes.add(jspMovimentacao, "Center");
        return taskMovimentacoes;
    }

    private void organizarBotoes(ComponentEvent e, JPanel p) {

        Map<String, JButton> botoesMapOrdenados = new TreeMap<String, JButton>();
        qtdComponentes = 0;

        for (Component c : p.getComponents()) {
            if (c instanceof JButton) {
                JButton botaoTemp = (JButton) c;
                qtdComponentes++;
                botoesMapOrdenados.put(botaoTemp.getText(), botaoTemp);
                p.remove(c);
            }
        }

        for (JButton bt : botoesMapOrdenados.values()) {
            p.add(bt);
        }

        Integer larguraTela = e.getComponent().getWidth() - totalMargemDesktop;

        Double qtdLinhas = 1d;

        p.setPreferredSize(new Dimension(larguraTela, qtdLinhas.intValue() * (alturaBtDesktop + 4)));
    }

    public void exeCadastroCliente() {

        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        CadastroCliente cad = new CadastroCliente(null, true, true, null);
        cad.setVisible(true);
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

    }

    public void exeManutencaoCliente() {
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        ManutencaoDeClientes man = new ManutencaoDeClientes(this, true, false);
        man.setVisible(true);
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    public void exeCadastroProduto() {
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//        cadastroItem = new cadastro.CadastroItem("Cadastro de Itens", false, "", false);
//        cadastroItem.setVisible(true);
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    public void exeManutencaoProduto() {
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    public void exeCadastroPedido() {
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    public void exeManutencaoPedido() {
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    JPanel jcpFundo = new JPanel();
    JPanel jcpCentral = new JPanel();
    JBlocoContainer2 blocoContainerCentral = new JBlocoContainer2();
    JScrollPane scrollPaneCentral;
    JCTaskPane taskPessoas = null;
    JCTaskPane taskProduto = null;
    JCTaskPane taskMovimentacoes = null;
    public int posicaoCursor = 0;
    public JBlocoContainer jcpDadosContainer = new JBlocoContainer();
    public JPanel jcpPessoas = new JPanel();
    public JPanel jcpMovimentacoes = new JPanel();
    public JPanel jcpProdutos = new JPanel();
    public JScrollPane jspPessoas = null;
    public JScrollPane jspMovimentacao = null;
    public JScrollPane jspProdutos = null;
    int controle = 0;
    int qtdComponentes = 0;
    public static int laguraBtDesktop = 145;
    public static int alturaBtDesktop = 88;
    public static int totalMargemDesktop = 320;
}
