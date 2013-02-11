


public class CanvasScreen
{
    private Canvas myCanvas;
    private int[][] positionMap;
    private int width;
    private int height;
    private int platformGround;
    
    public CanvasScreen(int width, int height, int ground, Canvas canvas){
        myCanvas = canvas;
        createPositionMap(width, height);
        this.width = width;
        this.height = height;
        platformGround = ground;
    }
    
    private void createPositionMap(int width, int height){
        positionMap = new int[width][height];
    }
    
    public int value(int x, int y){
        return positionMap[x][y];
    }
    
    public void setValue(int x, int y, int value){
        positionMap[x][y] = value;
    }
    
    public int[][] getScreen(){
        return positionMap;
    }
    
    public Canvas canvas(){
        return myCanvas;
    }
    
    public int width(){
        return width;
    }
    
    public int height(){
        return height;
    }
    
    public int platformGround(){
        return platformGround;
    }
    

}