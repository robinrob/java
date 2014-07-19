

public class TextSystem
{
        
        private InputReader reader;
        private Responder responder;
        
        
        public TextSystem()
        {
            reader = new InputReader();
            responder = new Responder();
        }
        
        public void start()
        {
        boolean finished = false;
        
        printWelcome();
        
        while(!finished) {
            String input = reader.getInput();
            input = input.trim();
            input = input.toLowerCase();
            
           if(input.startsWith("bye")){
               finished = true;
           }
           if(input.equals("bye")){
               finished = true;
            }
            else {
                String response = responder.generateResponse();
                System.out.println(response);
            }
        }
        printGoodbye();
    }
    
    private void printWelcome()
    {
        System.out.println("Hello mate!");
    }
    
    private void printGoodbye()
    {
        System.out.println("Nice talking to you mate!");
    }
}
    