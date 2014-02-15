package com.demo.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.demo.parsermanaager.ProductFetcher;

public class CountSignals {
	static final CountSignals countSignals = new CountSignals();

	public CountSignals() {

	}

	public static CountSignals getCountSingal() {
		return countSignals;
	}

	private static HashMap<String, ArrayList<Float>> values = new HashMap<String, ArrayList<Float>>();
	private static Map<String, Map<String, Float>> broadcastMap = new ConcurrentHashMap<String, Map<String, Float>>();
	private static Map<String, Float> broadcast = new ConcurrentHashMap<String, Float>();
	private static Map<String, ProductData> productDataMap = Collections.synchronizedMap(new HashMap<String, ProductData>());
	public static ProductData productData = null;

	public void readData(List<ProductData> prodcutDataList) {

		try {
			ArrayList<String> trades = new ArrayList<String>();
			ProductData productData = null;

			if (prodcutDataList.size() > 0) {
				productDataMap.clear();
				broadcastMap.clear();
			}
			for (int count = 0; count < prodcutDataList.size(); count++) {
				productData = prodcutDataList.get(count);
				if (productData != null) {
					productDataMap.put(productData.getId(), productData);
					trades.add(productData.getId());
					trades.add(productData.getRate());
					trades.add(productData.getType());
					trades.add(productData.getRate());
				}
			}

			Set<String> productname = new HashSet<String>();
			for (int i = 0; i < trades.size(); i++) {
				String name = trades.get(i++);
				ArrayList al = (ArrayList) values.get(name);
				if (al == null) {
					al = new ArrayList();
					for (int i1 = 0; i1 < 30; i1++) {
						al.add(Float.valueOf(0.0F));
					}
				} else {
					al.remove(0);
					al.add(Float.valueOf(Float.parseFloat(trades.get(i))));
				}

				values.put(name, al);
				float sum = 0.0F;
				for (int k = 0; k < al.size(); k++)
					sum += ((Float) al.get(k)).floatValue();
				sum /= 30F;
				float signal = Float.valueOf(Float.parseFloat(trades.get(i++)) - sum);
				broadcast.put(name, signal*100);
				//System.out.println(signal*100);
				String productType = trades.get(i++);
				float rate = Float.parseFloat(trades.get(i));

				if (!productname.add(name) && rate == 0.0)
					continue;
				if (broadcastMap.get(productType) != null) {
					broadcastMap.get(productType).put(name, signal);
				} else {
					Map<String, Float> map = new HashMap<String, Float>();
					map.put(name, signal);
					broadcastMap.put(productType, map);
				}
				recommandedProdcut();
				/*
				 * if (recommendBroadcastMap.get(productType) != null) {
				 * 
				 * if (recommendBroadcastMap.get(productType).get(name) != null
				 * && recommendBroadcastMap.get(productType).get(name) > signal
				 * &&
				 * ProductFetcher.filterWeekDaysOffProducts(productDataMap.get
				 * (name))) { recommendBroadcastMap.get(productType).put(name,
				 * signal); } } else { Map<String, Float> map = new
				 * HashMap<String, Float>(); map.put(name, signal);
				 * recommendBroadcastMap.put(productType, map); }
				 */
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}

	public static String readSignal(String name) {

		String s;
		try {
			Float f = broadcast.get(name);
			f = f * 10000;
			s = String.format("%.2f", f);
		} catch (Exception e) {
			s = "oops . Try Again...";
		}
		return s;
	}

	public String getLatestData(String name) {
		String s = "No Data Found ";
		try {
			ArrayList<Float> al = values.get(name);
			Float f = al.get(al.size() - 1);
			f = (float) Math.round(f * 100.0) / 100.0f;
			float dif = al.get(al.size() - 1) - al.get(al.size() - 2);
			if (dif > 0) {
				s = f.toString() + "y";
			} else {
				s = f.toString() + "n";
			}
		} catch (Exception e) {
			s = "oops . Try Again...";
		}
		return s;
	}

	public void recommandedProdcut() {
		Iterator<String> it = broadcast.keySet().iterator();
		float max = 0.0f;
		while (it.hasNext()) {
			String id = it.next();

			float f = broadcast.get(id);
			if (max < f) {
				if (productDataMap.get(id) != null) {
					if (ProductFetcher.isTradableCheck(productDataMap.get(id))) {
						max = f;
						productData = CountSignals.productDataMap.get(id);
					}
				}
			}
		}
	}

	public Map<String, ProductData> getProdcutMap() {
		if (productDataMap.size() <= 0) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				//e.printStackTrace();
			}
		}
		return productDataMap;
	}

	public static Map<String, Map<String, Float>> getBroadCastMap() {
		if (broadcastMap.size() <= 0) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
			}
		}
		return broadcastMap;
	}
}
