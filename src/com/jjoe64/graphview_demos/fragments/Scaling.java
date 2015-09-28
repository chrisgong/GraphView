/**
 * This file is licensed under MIT
 *
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Jonas Gehring
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.jjoe64.graphview_demos.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer.GridStyle;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;
import com.jjoe64.graphview_demos.MainActivity;
import com.jjoe64.graphview_demos.R;

/**
 * Created by jonas on 03.11.14.
 */
public class Scaling extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main, container,
				false);

		GraphView graph = (GraphView) rootView.findViewById(R.id.graph);

		DataPoint[] points = new DataPoint[100];
		for (int i = 0; i < points.length; i++) {
			points[i] = new DataPoint(i, -(Math.random() * 100 + 1));
		}
		LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(
				points);
		series.setThickness(1f);
		series.setOnDataPointTapListener(new OnDataPointTapListener() {

			@Override
			public void onTap(Series series, DataPointInterface dataPoint) {
				Log.e("cim", dataPoint.getX() + "----" + dataPoint.getY());
			}
		});

		graph.addSeries(series);
		// set manual X bounds
		graph.getViewport().setYAxisBoundsManual(true);
		graph.getViewport().setMinY(-200);
		graph.getViewport().setMaxY(200);

		graph.getViewport().setXAxisBoundsManual(true);
		graph.getViewport().setMinX(4);
		graph.getViewport().setMaxX(8);

		// enable scaling
		graph.getViewport().setScalable(true);
		graph.getGridLabelRenderer().setGridStyle(GridStyle.BOTH); // 网格 垂直，水平、无
		graph.getGridLabelRenderer().setHighlightZeroLines(false); // 中心线是否加粗
		graph.getGridLabelRenderer().setVerticalLabelsVisible(true); // y轴 刻度
		graph.getGridLabelRenderer().setHorizontalLabelsVisible(true);
		// graph.getGridLabelRenderer().setNumVerticalLabels(3); //X轴 条数
		graph.getGridLabelRenderer().setTextSize(10);
		graph.getGridLabelRenderer().setLabelsSpace(1);

		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				MainActivity.ARG_SECTION_NUMBER));
	}

}
