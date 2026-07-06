package control;

public class MagicSongPicker {
    private Timeline hours;
    private Timeline days;
    private Timeline months;

    public MagicSongPicker(Timeline hours, Timeline days, Timeline months) {
        this.hours = hours;
        this.days = days;
        this.months = months;
    }

    /*      User controllable settings of the magic song picker gimmick:
     *
     *      1) Genre Novelty -- How likely am I to get a genre I normally list to?
     *      2) Artist Novelty -- How likely am I to get a song by an artist I normally list to?
     *      3) bpm function of time -- more energetic music will be discriminated against
     *                                           at certain times of the day.
     *      4) history weight -- are songs listened to months ago going to impact my recommendation
     *                       as much as newly listened to ones.
     */


}
