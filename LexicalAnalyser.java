import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class LexicalAnalyser {
	public static List<Token> analyse(String input) throws NumberException, ExpressionException {
		String buffer = "";
        int operatorCounter = 0;
		int whitespaceCounter = 0;
		char finalCharacter = input.charAt(input.length()-1);
		List<Token> token = new ArrayList<Token>();
		for (int i = 0; i < input.length(); i++) {
			char initialCharacter = input.charAt(0);
			char currentCharacter = input.charAt(i);
			boolean characterIsFullStop = currentCharacter == '.';
			if (Character.isDigit(currentCharacter) || characterIsFullStop) {
				if (characterIsFullStop) {
					buffer += Character.toString(currentCharacter);
					if (input.matches(".[^\\d]") || initialCharacter != '0') {
						throw new NumberException();
					}
				} else {
                    if (operatorCounter == 1) {
						operatorCounter--;
					}
					if (whitespaceCounter % 2 != 0) {
						throw new ExpressionException();
					}
					buffer += Character.toString(currentCharacter);
				}
			} else if (currentCharacter == '+' || currentCharacter == '-' || currentCharacter == '*' || currentCharacter == '/') {
				operatorCounter++;
				if (operatorCounter == 2) {
					throw new ExpressionException();
				}
				token.add(new Token(Double.parseDouble(buffer)));
				buffer = "";
				token.add(new Token(Token.typeOf(currentCharacter)));
			} else if (!Character.isDigit(initialCharacter)) {
				throw new ExpressionException();
			} else if (Character.isWhitespace(currentCharacter)) {
				whitespaceCounter++;
			}
		}
        if (finalCharacter == '+' || finalCharacter == '-' || finalCharacter == '*' || finalCharacter == '/') {
			throw new ExpressionException();
		}
		token.add(new Token(Double.parseDouble(buffer)));
		return token;
	}
}
