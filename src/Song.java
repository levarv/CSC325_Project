import java.util.*;

public class Song {
    public enum Genre {BLUES,CLASSIC_ROCK,COUNTRY,DANCE,DISCO,FUNK,GRUNGE,HIP_HOP,JAZZ,METAL,NEW_AGE,OLDIES,OTHER,POP,R_B,RAP,REGGAE,ROCK,TECHNO,INDUSTRIAL,ALTERNATIVE,SKA,DEATH_METAL,PRANKS,SOUNDTRACK,EURO_TECHNO,AMBIENT,TRIP_HOP,VOCAL,JAZZ___FUNK,FUSION,TRANCE,CLASSICAL,INSTRUMENTAL,ACID,HOUSE,GAME,SOUND_CLIP,GOSPEL,NOISE,ALTERNATIVE_ROCK,BASS,SOUL,PUNK,SPACE,MEDITATIVE,INSTRUMENTAL_POP,INSTRUMENTAL_ROCK,ETHNIC,GOTHIC,DARKWAVE,TECHNO_INDUSTRIAL,ELECTRONIC,POP_FOLK,EURODANCE,DREAM,SOUTHERN_ROCK,COMEDY,CULT,GANGSTA,TOP_40,CHRISTIAN_RAP,POP_FUNK,JUNGLE,NATIVE_US,CABARET,NEW_WAVE,PSYCHADELIC,RAVE,SHOWTUNES,TRAILER,LO_FI,TRIBAL,ACID_PUNK,ACID_JAZZ,POLKA,RETRO,MUSICAL,ROCK_N_ROLL,HARD_ROCK,FOLK,FOLK_ROCK,NATIONAL_FOLK,SWING,FAST_FUSION,BEBOP,LATIN,REVIVAL,CELTIC,BLUEGRASS,AVANTGARDE,GOTHIC_ROCK,PROGRESSIVE_ROCK,PSYCHEDELIC_ROCK,SYMPHONIC_ROCK,SLOW_ROCK,BIG_BAND,CHORUS,EASY_LISTENING,ACOUSTIC,HUMOUR,SPEECH,CHANSON,OPERA,CHAMBER_MUSIC,SONATA,SYMPHONY,BOOTY_BASS,PRIMUS,PORN_GROOVE,SATIRE,SLOW_JAM,CLUB,TANGO,SAMBA,FOLKLORE,BALLAD,POWER_BALLAD,RHYTHMIC_SOUL,FREESTYLE,DUET,PUNK_ROCK,DRUM_SOLO,A_CAPELLA,EURO_HOUSE,DANCE_HALL,GOA,DRUM___BASS,CLUB_HOUSE,HARDCORE_TECHNO,TERROR,INDIE,BRITPOP,NEGERPUNK,POLSK_PUNK,BEAT,CHRISTIAN_GANGSTA_RAP,HEAVY_METAL,BLACK_METAL,CROSSOVER,CONTEMPORARY_CHRISTIAN,CHRISTIAN_ROCK,MERENGUE,SALSA,THRASH_METAL,ANIME,JPOP,SYNTHPOP,ABSTRACT,ART_ROCK,BAROQUE,BHANGRA,BIG_BEAT,BREAKBEAT,CHILLOUT,DOWNTEMPO,DUB,EBM,ECLECTIC,ELECTRO,ELECTROCLASH,EMO,EXPERIMENTAL,GARAGE,GLOBAL,IDM,ILLBIENT,INDUSTRO_GOTH,JAM_BAND,KRAUTROCK,LEFTFIELD,LOUNGE,MATH_ROCK,NEW_ROMANTIC,NU_BREAKZ,POST_PUNK,POST_ROCK,PSYTRANCE,SHOEGAZE,SPACE_ROCK,TROP_ROCK,WORLD_MUSIC,NEOCLASSICAL,AUDIOBOOK,AUDIO_THEATRE,NEUE_DEUTSCHE_WELLE,PODCAST,INDIE_ROCK,G_FUNK,DUBSTEP,GARAGE_ROCK,PSYBIENT;

    }

    private static final HashMap<Genre, Integer> UTILITY_MAP = instantiate();

    private static HashMap<Genre, Integer> instantiate() {
        HashMap<Genre, Integer> utilityMap = new HashMap<>();
        for (Genre g : Genre.values())
            utilityMap.put(g, 0);
        return utilityMap;
    }

    public static HashMap<Genre, Integer> getUtilityMap() {
        return new HashMap<>(UTILITY_MAP);
    }

    public static Song randomSong() {
        Random rng = new Random();
        ArrayList<Genre> tempList = new ArrayList<>(UTILITY_MAP.keySet());
        int rand = rng.nextInt(0, tempList.size());
        return new Song("name",1.0, 1, tempList.get(rand).toString(), "none");
    }

    private Genre genre;
    private String name;
    private Double loudness;
    private String artist;
    private int bpm;

    public Genre getGenre() {
        return genre;
    }

    public String getName() {
        return name;
    }

    public Double getLoudness() {
        return loudness;
    }

    public String getArtist() {
        return artist;
    }

    public int getBpm() {
        return bpm;
    }

    public Song(String name, Double loudness, int bpm, String genre, String artist) {
        this.name = name;
        this.loudness = loudness;
        this.bpm = bpm;
        this.genre = Genre.valueOf(removeSpecialChars(genre));
        this.artist = artist;
    }

    public String removeSpecialChars(String s){
        return s.replaceAll("[-+$ !/]", "_");
    }

    @Override
    public String toString() {
        return "Song{" +
                "genre=" + genre +
                ", name='" + name + '\'' +
                ", loudness=" + loudness +
                ", artist='" + artist + '\'' +
                ", bpm=" + bpm +
                '}';
    }
}
