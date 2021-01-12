package pl.roslon.springbootmapinit;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class Covid19Parser {

    private static final String URL_CONFIRMED = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private static final String URL_DEATHS = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";

    public List<Point> getCovidData() throws IOException {
        List<Point> points = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();

        String restTemplateForObject = restTemplate.getForObject(URL_CONFIRMED, String.class);

        StringReader stringReader = new StringReader(restTemplateForObject);

        try {
            CSVParser parseConfirmed = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(stringReader);

            for (CSVRecord strings : parseConfirmed) {
                double lat = 0;
                    if (strings.get("Lat").isEmpty()){
                        lat = 0;
                    }else {
                       lat = Double.parseDouble(strings.get("Lat"));
                    }

                double lon = 0;
                if (strings.get("Long").isEmpty()){
                    lon = 0;
                }else {
                    lon = Double.parseDouble(strings.get("Long"));
                }
                String text = "Zakazeni: " + strings.get("1/10/21");
                points.add(new Point(lat, lon, text));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return points;
    }
}