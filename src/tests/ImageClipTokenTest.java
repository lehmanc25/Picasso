package tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.StringReader;
import java.io.StreamTokenizer;
import picasso.parser.tokens.TokenFactory;
import picasso.parser.tokens.functions.FunctionToken;
import picasso.parser.tokens.Token;

public class ImageClipTokenTest {

    @Test
    public void testTokenizationOfImageClip() {
        String input = "imageClip(\"image.jpg\", 0.5, -0.5)";
        StreamTokenizer tokenizer = new StreamTokenizer(new StringReader(input));

        // Assuming your tokenizer setup here (if any specific configuration is needed)
        // ...

        try {
            tokenizer.nextToken(); // Process the first token
            Token token = TokenFactory.parse(tokenizer);

            assertNotNull("Token should not be null", token);
            assertTrue("Token should be an instance of FunctionToken", token instanceof FunctionToken);

            // Check the specific function name
            String functionName = ((FunctionToken) token).getFunctionName();
            assertEquals("imageClip", functionName, "Token function name should be 'imageClip'");

        } catch (Exception e) {
            fail("Exception during tokenization: " + e.getMessage());
        }
    }

    // Additional test cases for different scenarios, edge cases, etc.
    // ...
}
