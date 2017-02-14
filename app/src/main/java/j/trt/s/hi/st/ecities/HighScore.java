package j.trt.s.hi.st.ecities;



public class HighScore {
    public int num;
    public String player;
    public int victory;
    public int defeat;
    public double coeff;

    public HighScore(int num, String player, int victory, int defeat, double coeff) {
        this.num = num;
        this.player = player;
        this.victory = victory;
        this.defeat = defeat;
        this.coeff = coeff;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getVictory() {
        return victory;
    }

    public void setVictory(int victory) {
        this.victory = victory;
    }

    public int getDefeat() {
        return defeat;
    }

    public void setDefeat(int defeat) {
        this.defeat = defeat;
    }

    public double getCoeff() {
        return coeff;
    }

    public void setCoeff(double coeff) {
        this.coeff = coeff;
    }
}
