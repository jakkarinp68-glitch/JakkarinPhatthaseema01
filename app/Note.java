public class Note {
    String title;
    String content;
    String createdDate;

    void getSummary() {
        System.out.println(this.title + " " + this.content + " " + this.createdDate);
    }
}
