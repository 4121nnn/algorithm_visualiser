package berserk.algoviz;

import berserk.algoviz.algorithms.Algorithm;
import berserk.algoviz.algorithms.impl.sort.BubbleSort;
import berserk.algoviz.algorithms.utils.AlgoUtils;
import berserk.algoviz.model.AlgoSettings;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Algoviz {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(Algoviz.class, args);
	}

}
