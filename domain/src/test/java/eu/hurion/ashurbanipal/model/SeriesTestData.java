package eu.hurion.ashurbanipal.model;

public class SeriesTestData {
    public static final Book PHILOSOPHER_STONE = new Book("Harry Potter and the Philosopher Stone");
    public static final Book CHAMBER_OF_SECRETS = new Book("Harry Potter and the Chamber of Secrets");
    public static final Book PRISONER_OF_AZKABAN = new Book("Harry Potter and the Prisoner of Azkaban");
    public static final Book GOBLET_OF_FIRE = new Book("Harry Potter and the Goblet of Fire");
    public static final Book ORDER_OF_THE_PHOENIX = new Book("Harry Potter and the Order of the Phoenix");
    public static final Book HALF_BLOOD_PRINCE = new Book("Harry Potter and the Half-blood Prince");
    public static final Book DEATHLY_HALLOWS = new Book("Harry Potter and the Deathly Hallows");
    public static final Series HARRY_POTTER = new Series("Harry Potter", PHILOSOPHER_STONE, CHAMBER_OF_SECRETS,
            PRISONER_OF_AZKABAN, GOBLET_OF_FIRE, ORDER_OF_THE_PHOENIX, HALF_BLOOD_PRINCE, DEATHLY_HALLOWS);
}