import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Parse {

    private final static Logger LOGGER =
            LoggerFactory.getLogger(Parse.class);

    //global variable to track character position value during recursion
    int position=0;

    //hashmap to map the let variable to value
    HashMap<String,Integer> map = new HashMap();
        public int parse(String s) throws Exception{
            String newString = s.replace(" ", "");
            char[] arr = newString.toCharArray();
            return parseExpression(arr);

        }

        //checks whether next step is an expression or just return integer
        public boolean isInteger (char[] arr, int position) throws Exception{
            String s = Character.toString(arr[position]);
            try{
                Integer.parseInt(s);
            }catch(Exception e){
                return false;
            }
            return true;
        }

        //builds string from valid characters during iteration
        public String parseCharacters(char[] arr){
            StringBuilder s = new StringBuilder();
            while(Character.isLetter(arr[position])){
                s.append(arr[position]);
                position++;
            }
            return s.toString();
        }

        //builds integer from valid int characters during iteration
        public int parseInteger(char[] arr){
            StringBuilder s = new StringBuilder();
            while(arr[position]>='0' && arr[position]<='9' ){
                s.append(arr[position]);
                position++;
            }

            return Integer.parseInt(s.toString());
        }

        //resolves one expression and returns resulting integer or simply returns integer if no expression
        public int parseExpression(char[] arr) throws Exception {
            if (arr[position] == 'a' && arr[position + 1] == 'd' && arr[position + 2] == 'd' && arr[position + 3] == '(') {
                position = position + 4;
                int leftIntegerNode = parseExpression(arr);
                if (arr[position] != ',') {
                    String msg= "Comma Expected";
                    throw new Exception(msg);
                }
                position++;
                int rightIntegerNode = parseExpression(arr);
                if (arr[position] != ')') {
                    String msg= "Closing Parenthesis Expected";
                    throw new Exception(msg);
                }
                position++;
                return leftIntegerNode+rightIntegerNode;
            } else if (arr[position] == 'd' && arr[position + 1] == 'i' && arr[position + 2] == 'v' && arr[position + 3] == '(') {
                    position = position + 4;
                    int leftIntegerNode = parseExpression(arr);
                    if (arr[position] != ',') {
                        String msg= "Comma Expected";
                        throw new Exception(msg);
                    }
                    position++;
                    int rightIntegerNode = parseExpression(arr);
                    if (arr[position] != ')') {
                        String msg= "Closing Parenthesis Expected";
                        throw new Exception(msg);
                    }
                    position++;
                    return leftIntegerNode/rightIntegerNode;
            } else if (arr[position] == 'm' && arr[position + 1] == 'u' && arr[position + 2] == 'l' && arr[position + 3] == 't' && arr[position + 4] == '(') {
                position = position + 5;
                int leftIntegerNode = parseExpression(arr);
                if (arr[position] != ',') {
                    String msg= "Comma Expected";
                    throw new Exception(msg);
                }
                position++;
                int rightIntegerNode = parseExpression(arr);
                if (arr[position] != ')') {
                    String msg= "Closing Parenthesis Expected";
                    throw new Exception(msg);
                }
                position++;
                return leftIntegerNode*rightIntegerNode;
            } else if (arr[position] == 's' && arr[position + 1] == 'u' && arr[position + 2] == 'b' && arr[position + 3] == '(') {
                position = position + 4;
                int leftIntegerNode = parseExpression(arr);
                if (arr[position] != ',') {
                    String msg= "Comma Expected";
                    throw new Exception(msg);
                }
                position++;
                int rightIntegerNode = parseExpression(arr);
                if (arr[position] != ')') {
                    String msg= "Closing Parenthesis Expected";
                    throw new Exception(msg);
                }
                position++;
                return leftIntegerNode-rightIntegerNode;

            } else if (arr[position] == 'l' && arr[position + 1] == 'e' && arr[position + 2] == 't' && arr[position + 3] == '(') {
                position = position + 4;
                String s =(parseCharacters(arr));
                if (arr[position] != ',') {
                    String msg= "Comma Expected";
                    throw new Exception(msg);
                }
                position++;
                int value = parseExpression(arr);
                map.put(s, value);
                if (arr[position] != ',') {
                    String msg= "Comma Expected";
                    throw new Exception(msg);
                }
                position++;
                int anotherValue = parseExpression(arr);
                if (arr[position] != ')') {
                    String msg= "Closing Parenthesis Expected";
                    throw new Exception(msg);
                }
                position++;
                return anotherValue;

            } else if(isInteger(arr, position)){
                return parseInteger(arr);
            }else{
                String s = parseCharacters(arr);
                if(map.containsKey(s)){
                    return map.get(s);
                } else {
                    String msg= "Bad Expression";
                    throw new Exception(msg);
                }
            }
        }

    public static void main(String[] args) {
            LOGGER.info("==========Calculator Parser==========");
            Parse par = new Parse();
            try {
                String aCalculatableString= "let(a, let(b, 10, add(b, b)), let(b, 20 , add(a, b)))";
                LOGGER.info("Calculating: " + aCalculatableString);
                LOGGER.info("Answer is: " + par.parse(aCalculatableString));
            } catch (Exception e) {
                LOGGER.error(e.getMessage() + " at position " + par.position);
                LOGGER.debug(e.getMessage() + " at position " + par.position);
            }


        }
    }



