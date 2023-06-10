package com.example.task2spring.service;

import com.example.task2spring.model.Game;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.*;

@Service
public class TaskService {
    public List<Game> getBets() {
        Document doc = null;
        try {
            doc = Jsoup.connect("https://lite.betbonanza.com/")
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .referrer("https://www.google.com")
                    .get();
        } catch (IOException ignored) {
        }
        assert doc != null;
        Elements games = doc.getElementsByClass("highlights--item");
        List<Game> _games = new ArrayList<>();
        for (Element game : games) {
            Game _game = new Game();
            String date = game.getElementsByClass("time").text().split(" ", 2)[1].trim();
            try {
                SimpleDateFormat parser = new SimpleDateFormat("d MMM HH:mm", Locale.ENGLISH);
                Calendar _date = Calendar.getInstance();
                _date.setTime(parser.parse(date));
                _date.set(Calendar.YEAR, Year.now().getValue());
                _game.setDateTime(_date);
            } catch (ParseException ignored) {
            }
            Elements clubs = game.getElementsByClass("clubs").select("p");
            _game.setFirstTeam(clubs.get(0).text().trim());
            _game.setSecondTeam(clubs.get(1).text().trim());
            String[] meta = game.getElementsByClass("meta").text().split("/");
            _game.setSports(meta[0].trim());
            _game.setCountry(meta[1].trim());
            _game.setLeague(meta[2].trim());
            _game.setLink(game.getElementsByClass("std-pd pt-10").select("a").attr("href"));
            _games.add(_game);
        }
        return _games;
    }
}