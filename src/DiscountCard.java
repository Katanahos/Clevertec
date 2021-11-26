public enum DiscountCard {
    /** База данных скидочных карт */
    CARD1(1,"Jack"),
    CARD2(2,"Michail"),
    CARD3(3,"Dasha");


    public int cardID;          // Номер карточки
    private String cardHolder;  // Владелец


    DiscountCard(int cardID, String cardHolder) {
        this.cardID = cardID;
        this.cardHolder = cardHolder;
    }

    public int getCardID() {
        return cardID;
    }
}
