/*
 * JCBlocoContainer.java
 *
 * Created on 13 de Junho de 2007, 10:53
 */

package utilitario;

import modelos.JCTaskPane;


/**
 *
 * @author 
 */
public class JBlocoContainer2 extends javax.swing.JComponent implements javax.swing.Scrollable {
    
    javax.swing.GroupLayout jCBlocoContainer1Layout = new javax.swing.GroupLayout(this);
    javax.swing.GroupLayout.ParallelGroup grupoHorizontal = jCBlocoContainer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
    javax.swing.GroupLayout.SequentialGroup grupoHorizontalSequencial = jCBlocoContainer1Layout.createSequentialGroup();
    javax.swing.GroupLayout.ParallelGroup grupoHorizontalSequencialParalelo = jCBlocoContainer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
    javax.swing.GroupLayout.ParallelGroup grupoVertical = jCBlocoContainer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
    javax.swing.GroupLayout.SequentialGroup grupoVerticalSequencial = jCBlocoContainer1Layout.createSequentialGroup();

    public javax.swing.ButtonGroup grupoBotao = new javax.swing.ButtonGroup();
    
    private boolean blocoLivres = false;
    /** Creates new form BeanForm */
    public JBlocoContainer2() {
        initComponents();
//        setMinimumSize(new java.awt.Dimension(400,400));
    }

    public JBlocoContainer2(java.util.Vector<JCTaskPane> taskPanes) {
        this();
        setJCTaskPanes(taskPanes);
    }
    
    public void setJCTaskPanes(java.util.Vector<JCTaskPane> tasks){
        grupoVerticalSequencial.addContainerGap();
        for (int i = 0; i < tasks.size(); i++) {
            grupoHorizontalSequencialParalelo.addComponent(tasks.get(i), javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE);
            grupoVerticalSequencial.addComponent(tasks.get(i), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
            grupoVerticalSequencial.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
            grupoBotao.add(tasks.get(i).getJcbTitulo());
        }
        tasks.get(0).setExpanded(true);
        grupoVerticalSequencial.addContainerGap(10, Short.MAX_VALUE);
    }
    
    private void initComponents() {
        jCBlocoContainer1Layout.setHorizontalGroup(grupoHorizontal);
        
        //Parte Horizontal
        grupoHorizontal.addGroup(grupoHorizontalSequencial);
        grupoHorizontalSequencial.addContainerGap();
        grupoHorizontalSequencial.addGroup(grupoHorizontalSequencialParalelo);
        grupoHorizontalSequencial.addContainerGap();
        jCBlocoContainer1Layout.setVerticalGroup(grupoVertical);
        
        grupoVertical.addGroup(grupoVerticalSequencial);
        grupoVerticalSequencial.addContainerGap();
        setLayout(jCBlocoContainer1Layout);
   }

    public boolean isBlocoLivres() {
        return blocoLivres;
    }

    public void setBlocoLivres(boolean blocoLivres) {
        this.blocoLivres = blocoLivres;
    }
    
  
public java.awt.Dimension getPreferredScrollableViewportSize() {
        return getPreferredSize();
    }
    
  
public int getScrollableUnitIncrement(java.awt.Rectangle visibleRect, int orientation, int direction) {
        return 10;
    }
    
  
public int getScrollableBlockIncrement(java.awt.Rectangle visibleRect, int orientation, int direction) {
        return 10;
    }
    
  
public boolean getScrollableTracksViewportWidth() {
        return true;
    }
    
  
public boolean getScrollableTracksViewportHeight() {
        if (getParent() instanceof javax.swing.JViewport) {
            return (((javax.swing.JViewport)getParent()).getHeight() > getPreferredSize().height);
        } else {
            return false;
        }
    }

    
    // Variables declaration - do not modify                     
    // End of variables declaration                   
    
}
