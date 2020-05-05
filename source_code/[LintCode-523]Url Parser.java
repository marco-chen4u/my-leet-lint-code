/***
* LintCode 523. Url Parser
Parse a html page, extract the Urls in it.
Example
	Example 1:
		Input:
			<html>
			<body>
			  <div>
				<a href="http://www.google.com" class="text-lg">Google</a>
				<a href="http://www.facebook.com" style="display:none">Facebook</a>
			  </div>
			  <div>
				<a href="https://www.linkedin.com">Linkedin</a>
				<a href = "http://github.io">LintCode</a>
			  </div>
			</body>
			</html>
		Output:
			[
			"http://www.google.com",
			"http://www.facebook.com",
			"https://www.linkedin.com",
			"http://github.io"
			]
	Example 2:
		Input:
			<html>
			<body>
			  <div>
				<a  HREF =    "http://www.google.com" class="text-lg">Google</a>
				<a  HREF = "http://www.facebook.com" style="display:none">Facebook</a>
			  </div>
			  <div>
				<a href="https://www.linkedin.com">Linkedin</a>
				<a href = "http://github.io">LintCode</a>
			  </div>
			</body>
			</html>
		Output:
			[
			"http://github.io",
			"http://www.facebook.com",
			"http://www.google.com",
			"https://www.linkedin.com"
			]
		Notice
			Use regex to parse html.
***/

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class HtmlParser {
    /*
     * @param content: content source code
     * @return: a list of links
     */
    public List<String> parseUrls(String content) {
        List<String> result = new ArrayList<String>();
        
        // check corner case
        if (content == null || content.length() == 0) {
            return result;
        }
        
        String regEx = "\\s*(?i)href\\s*=\\s*(\"|')+([^\"'>\\s]*)";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(content);
        
        String url = null;
        
        while (matcher.find()) {
            url = matcher.group(2);
            
            if (url.length() == 0 || url.startsWith("#")) {
                continue;
            }
            
            result.add(url);
        }
        
        return result;
    }
}