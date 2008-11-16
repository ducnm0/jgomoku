 /*
  *Copyright 2008 Cristian Achim
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  *
  * http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing,
  * software distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  *
  */

package jgomoku;

import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.RenderingHints;

public class GraphicalInterface extends javax.swing.JPanel {

    private BufferedImage boardImage;

    private BufferedImage blackStone;
    private BufferedImage whiteStone;
    private BufferedImage middleNode;
    private BufferedImage northNode;
    private BufferedImage northEastNode;
    private BufferedImage eastNode;
    private BufferedImage southEastNode;
    private BufferedImage southNode;
    private BufferedImage southWestNode;
    private BufferedImage westNode;
    private BufferedImage northWestNode;

    private static int MAXROW;
    private static int MAXCOLUMN;

    private int boardImageHeight;
    private int boardImageWidth;

    GraphicalInterfaceController gic;

    private void imageInitialisation(){
        InputStream imageReader;

        //loading images from files in artwork folder in jar file
        try{
            imageReader=this.getClass().getResourceAsStream("/artwork/blackstone.png");
            blackStone=ImageIO.read(imageReader);
            imageReader.close();

            imageReader=this.getClass().getResourceAsStream("/artwork/whitestone.png");
            whiteStone=ImageIO.read(imageReader);
            imageReader.close();

            imageReader=this.getClass().getResourceAsStream("/artwork/middlenode.png");
            middleNode=ImageIO.read(imageReader);
            imageReader.close();

            imageReader=this.getClass().getResourceAsStream("/artwork/northnode.png");
            northNode=ImageIO.read(imageReader);
            imageReader.close();

            imageReader=this.getClass().getResourceAsStream("/artwork/northeastnode.png");
            northEastNode=ImageIO.read(imageReader);
            imageReader.close();

            imageReader=this.getClass().getResourceAsStream("/artwork/eastnode.png");
            eastNode=ImageIO.read(imageReader);
            imageReader.close();

            imageReader=this.getClass().getResourceAsStream("/artwork/southeastnode.png");
            southEastNode=ImageIO.read(imageReader);
            imageReader.close();

            imageReader=this.getClass().getResourceAsStream("/artwork/southnode.png");
            southNode=ImageIO.read(imageReader);
            imageReader.close();

            imageReader=this.getClass().getResourceAsStream("/artwork/southwestnode.png");
            southWestNode=ImageIO.read(imageReader);
            imageReader.close();

            imageReader=this.getClass().getResourceAsStream("/artwork/westnode.png");
            westNode=ImageIO.read(imageReader);
            imageReader.close();

            imageReader=this.getClass().getResourceAsStream("/artwork/northwestnode.png");
            northWestNode=ImageIO.read(imageReader);
            imageReader.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /** Creates new form GInterface */
    public GraphicalInterface(GraphicalInterfaceController gic , int maxrow , int maxcolumn) {
        int row , column;

        this.gic=gic;

        MAXROW=maxrow;
        MAXCOLUMN=maxcolumn;

        imageInitialisation();

        initComponents();

        newGameButton.setEnabled(false);
        saveGameButton.setEnabled(false);

        boardCanvas.setSize(450, 450);
        boardImage=new BufferedImage(450 , 450 , BufferedImage.TYPE_INT_ARGB);

        for(row=1 ; row <= MAXROW ; row++){
            for(column=1 ; column <= MAXCOLUMN ; column++){
                drawBackground(row , column);
            }
        }
        flipBackImage();
    }

    public void drawBackgroundCell(int row , int column){
        drawBackground(row+1 , column+1);
        flipBackImage();
    }

    private void drawBackground(int row , int column){
        BufferedImage img;

        if(row == 1 && column == 1){
            img=northWestNode;
        }
        else if(row == 1 && column==MAXCOLUMN){
            img=northEastNode;
        }
        else if(row==MAXROW && column==1){
            img=southWestNode;
        }
        else if(row==MAXROW && column==MAXCOLUMN){
            img=southEastNode;
        }
        else if(row==1){
            img=northNode;
        }
        else if(row==MAXROW){
            img=southNode;
        }
        else if(column==1){
            img=westNode;
        }
        else if(column==MAXCOLUMN){
            img=eastNode;
        }
        else{
            img=middleNode;
        }

        drawBackImage(img , row , column);
    }

    public void drawBlackStone(int row , int column){
        BufferedImage img=blackStone;
        drawBackImage(img , row+1 , column+1);
        flipBackImage();
    }

    public void drawWhiteStone(int row , int column){
        BufferedImage img=whiteStone;
        drawBackImage(img , row+1 , column+1);
        flipBackImage();
    }

    private void drawBackImage(Image img , int row , int column){
        int width , height;
        int cellWidth , cellHeight;
        int imageWidth , imageHeight;
        int scaledWidth , scaledHeight;
        Graphics2D g;
        BufferedImage scaledImage;
        
        height=boardCanvas.getHeight();
        width=boardCanvas.getWidth();

        cellWidth=width/MAXCOLUMN;
        cellHeight=height/MAXROW;

        imageWidth=img.getWidth(this);
        imageHeight=img.getHeight(this);

        g=(Graphics2D) boardImage.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION , RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(img , cellWidth * (column - 1) , cellHeight * (row - 1) , cellWidth , cellHeight , this);
        //System.out.println(cellWidth * (column - 1) + " " + cellHeight * (row - 1));
    }

    private void flipBackImage(){
        BoardCanvas bc=(BoardCanvas) boardCanvas;
        bc.updateBackImage(boardImage);
        bc.repaint();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        boardCanvas = new BoardCanvas();
        previousMoveButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        nextMoveButton = new javax.swing.JButton();
        whitePlayerComboBox = new javax.swing.JComboBox();
        startGameButton = new javax.swing.JButton();
        blackPlayerComboBox = new javax.swing.JComboBox();
        newGameButton = new javax.swing.JButton();
        saveGameButton = new javax.swing.JButton();
        loadGameButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        boardCanvas.setBackground(new java.awt.Color(232, 196, 25));
        boardCanvas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                boardCanvasMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boardCanvasMouseExited(evt);
            }
        });
        boardCanvas.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                boardCanvasMouseMoved(evt);
            }
        });

        previousMoveButton.setText("Previous move");
        previousMoveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                previousMoveButtonMouseClicked(evt);
            }
        });

        jLabel1.setText("black player");

        nextMoveButton.setText("Next move");
        nextMoveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nextMoveButtonMouseClicked(evt);
            }
        });

        whitePlayerComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Human", "Computer" }));

        startGameButton.setText("Start game");
        startGameButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                startGameButtonMouseClicked(evt);
            }
        });

        blackPlayerComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Human", "Computer" }));

        newGameButton.setText("New game");
        newGameButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newGameButtonMouseClicked(evt);
            }
        });

        saveGameButton.setText("Save game");
        saveGameButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveGameButtonMouseClicked(evt);
            }
        });

        loadGameButton.setText("Load game");
        loadGameButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loadGameButtonMouseClicked(evt);
            }
        });

        jLabel2.setText("white player");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(boardCanvas, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(previousMoveButton, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nextMoveButton, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .addComponent(whitePlayerComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, 121, Short.MAX_VALUE)
                    .addComponent(startGameButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .addComponent(blackPlayerComboBox, 0, 121, Short.MAX_VALUE)
                    .addComponent(newGameButton, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .addComponent(saveGameButton, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .addComponent(loadGameButton, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(boardCanvas, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(newGameButton)
                .addGap(8, 8, 8)
                .addComponent(saveGameButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(loadGameButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(blackPlayerComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(whitePlayerComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(startGameButton)
                .addGap(44, 44, 44)
                .addComponent(previousMoveButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nextMoveButton)
                .addGap(40, 40, 40))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void newGameButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newGameButtonMouseClicked
        // TODO add your handling code here:
}//GEN-LAST:event_newGameButtonMouseClicked

    private void saveGameButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveGameButtonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_saveGameButtonMouseClicked

    private void loadGameButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loadGameButtonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_loadGameButtonMouseClicked

    private void startGameButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_startGameButtonMouseClicked
        // TODO add your handling code here:
        boolean blackHuman , whiteHuman;

        if(blackPlayerComboBox.getSelectedIndex() == 0){
            blackHuman=true;
        }
        else{
            blackHuman=false;
        }
        if(whitePlayerComboBox.getSelectedIndex() == 0){
            whiteHuman=true;
        }
        else{
            whiteHuman=false;
        }

        gic.startGame(blackHuman , whiteHuman);
    }//GEN-LAST:event_startGameButtonMouseClicked

    private void previousMoveButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_previousMoveButtonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_previousMoveButtonMouseClicked

    private void nextMoveButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextMoveButtonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_nextMoveButtonMouseClicked

    private void boardCanvasMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boardCanvasMouseMoved
        // TODO add your handling code here:
        int xPress , yPress;
        int canvasWidth , canvasHeight;
        int cellWidth , cellHeight;
        int row , column;

        xPress=evt.getX();
        yPress=evt.getY();

        canvasWidth=boardCanvas.getWidth();
        canvasHeight=boardCanvas.getHeight();

        cellWidth=(int)((float)canvasWidth / (float)MAXROW);
        cellHeight=(int)((float)canvasHeight / (float)MAXCOLUMN);

        row=(int) ((float)yPress /  (float) cellHeight);
        column=(int)((float)xPress /  (float)cellWidth);

        gic.mouseMoved(row, column);
    }//GEN-LAST:event_boardCanvasMouseMoved

    private void boardCanvasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boardCanvasMouseClicked
        // TODO add your handling code here:
        int xPress , yPress;
        int canvasWidth , canvasHeight;
        int cellWidth , cellHeight;
        int row , column;

        xPress=evt.getX();
        yPress=evt.getY();

        canvasWidth=boardCanvas.getWidth();
        canvasHeight=boardCanvas.getHeight();

        cellWidth=(int)((float)canvasWidth / (float)MAXROW);
        cellHeight=(int)((float)canvasHeight / (float)MAXCOLUMN);

        row=(int) ((float)yPress /  (float) cellHeight);
        column=(int)((float)xPress /  (float)cellWidth);

        gic.mouseClicked(row, column);
    }//GEN-LAST:event_boardCanvasMouseClicked

    private void boardCanvasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boardCanvasMouseExited
        // TODO add your handling code here:
        gic.mouseMoved(-1 , -1);
    }//GEN-LAST:event_boardCanvasMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox blackPlayerComboBox;
    private java.awt.Canvas boardCanvas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    public javax.swing.JButton loadGameButton;
    public javax.swing.JButton newGameButton;
    public javax.swing.JButton nextMoveButton;
    public javax.swing.JButton previousMoveButton;
    public javax.swing.JButton saveGameButton;
    public javax.swing.JButton startGameButton;
    private javax.swing.JComboBox whitePlayerComboBox;
    // End of variables declaration//GEN-END:variables

}
