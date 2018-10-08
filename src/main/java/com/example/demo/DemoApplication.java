package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import com.example.demo.agroknow.cerebro.syngenta.varifield.seedrate.PredictionSeed;
import com.example.demo.agroknow.cerebro.syngenta.varifield.seedrate.PredictionSeedComplex;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.agroknow.cerebro.syngenta.varifield.enumerations.*;
import com.example.demo.agroknow.cerebro.syngenta.varifield.exceptions.CropNotAvailableException;
import com.example.demo.agroknow.cerebro.syngenta.varifield.exceptions.NoValidModelException;
import com.example.demo.agroknow.cerebro.syngenta.varifield.seedrate.SeedRate;

import java.net.URISyntaxException;

@SpringBootApplication
@RestController
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/")
	public String hello() {
		return "hello world!";
	}

	@RequestMapping(
			value = "/yields",
			params = {
					"crop",
					"soil_texture",
					"soil_type",
					"night_min",
					"day_max",
					"prec_sum",
					"avg_nt",
					"avg_dt",
					"avg_prec"
			}
	)
	public @ResponseBody Object yields(
			@RequestParam(value = "crop") Crop crop,
			@RequestParam(value = "soil_texture") SoilTexture soilTexture,
			@RequestParam(value = "soil_type") SoilType soilType,
			@RequestParam(value = "night_min") double nightMin,
			@RequestParam(value = "day_max") double dayMax,
			@RequestParam(value = "prec_sum") double precSum,
			@RequestParam(value = "avg_nt") double avgNT,
			@RequestParam(value = "avg_dt") double avgDT,
			@RequestParam(value = "avg_prec") double avgPrec) {

		try {
			SeedRate sr = new SeedRate(
					crop,
					soilTexture,
					soilType,
					nightMin,
					dayMax,
					precSum,
					avgNT,
					avgDT,
					avgPrec,
					PredictionTarget.YIELD
			);
			sr.findOptimumSeedRate();
			PredictionSeed ps = sr.getOptimumPrediction();
			ps.getPrediction();
			ps.getSeedRate();
			return ps;

		} catch (CropNotAvailableException e) {
			e.printStackTrace();
			return e;
		} catch (NoValidModelException e) {
			e.printStackTrace();
			return e;
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return e;
		}
	}

	@RequestMapping(
			value = "/ears",
			params = {
					"crop",
					"soil_texture",
					"soil_type",
					"night_min",
					"day_max",
					"prec_sum",
					"avg_nt",
					"avg_dt",
					"avg_prec"
			}
	)
	public @ResponseBody Object ears(
			@RequestParam(value = "crop") Crop crop,
			@RequestParam(value = "soil_texture") SoilTexture soilTexture,
			@RequestParam(value = "soil_type") SoilType soilType,
			@RequestParam(value = "night_min") double nightMin,
			@RequestParam(value = "day_max") double dayMax,
			@RequestParam(value = "prec_sum") double precSum,
			@RequestParam(value = "avg_nt") double avgNT,
			@RequestParam(value = "avg_dt") double avgDT,
			@RequestParam(value = "avg_prec") double avgPrec) {

		try {
			SeedRate sr = new SeedRate(
					crop,
					soilTexture,
					soilType,
					nightMin,
					dayMax,
					precSum,
					avgNT,
					avgDT,
					avgPrec,
					PredictionTarget.EARS
			);
			sr.findOptimumSeedRate();
			PredictionSeedComplex ps = (PredictionSeedComplex) 	sr.getOptimumPrediction();
			ps.getEarsPrediction();
			ps.getPrediction();
			ps.getSeedRate();
			return ps;

		} catch (CropNotAvailableException e) {
			e.printStackTrace();
			return e;
		} catch (NoValidModelException e) {
			e.printStackTrace();
			return e;
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return e;
		}
	}
}