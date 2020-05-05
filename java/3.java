package jyaml;

public class Order {

    private String id;
    private Article[] articles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Article[] getArticles() {
        return articles;
    }

    public void setArticles(Article[] articles) {
        this.articles = articles;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer("[Command id='")
            .append(id)
            .append("', articles='");
        for (int i=0; i<articles.length; i++) {
            Article article = articles[i];
            buffer.append(article.toString());
            if (i<articles.length-1) buffer.append(", ");
        }
        buffer.append("]");
        return buffer.toString();
    }

}
