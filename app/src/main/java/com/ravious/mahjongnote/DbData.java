package com.ravious.mahjongnote;

/**
 * Created by yuuki on 7/18/16.
 */

public class DbData {
    //ある日の総合データ(日付1日につき1レコード)
    //対戦入力終了時、変更時のみデータの挿入が行われれる
    //総合結果画面(トップページ)で参照される
    protected String date;
    protected String total_divident;
    protected String game_payment;
    protected String number_of_games;
    protected String average_rank;
    protected String first;
    protected String second;
    protected String third;
    protected String fourth;
    protected String first_player;
    protected String second_player;
    protected String third_player;
    protected String fourth_player;

    //各対戦データ(ゲーム1回につき1レコード)
    //1対戦終了時ごとに、挿入される
    protected String date_in_game;
    protected String number_of_game;
    protected String point1;
    protected String point2;
    protected String point3;
    protected String point4;



    public DbData(String date, String total_divident, String game_payment,
                  String number_of_games, String average_rank, String first,
                  String second, String third, String fourth, String first_player,
                  String second_player, String third_player, String fourth_player) {
        this.date = date;
        this.total_divident = total_divident;
        this.game_payment = game_payment;
        this.number_of_games = number_of_games;
        this.average_rank = average_rank;
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
        this.first_player = first_player;
        this.second_player = second_player;
        this.third_player = third_player;
        this.fourth_player = fourth_player;
    }

    public DbData(String date_in_game, String number_of_game, String point1,
                  String point2, String point3, String point4){
        this.date_in_game = date_in_game;
        this.number_of_game = number_of_game;
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        this.point4 = point4;
    }

    public String getDate() {
        return date;
    }

}


