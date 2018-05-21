package org.songdan.tij.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 16 五月 2018
 */
public class IterateGroup {

	public static void main(String[] args) {
		List<Integer> shelfBeans = generate(170);
		int batch = 50;
		for (int i = 0; i < shelfBeans.size();) {
			List<Integer> codeList = Lists.newArrayListWithExpectedSize(batch);
			for (int j = 0; j < batch; j++) {
				if (i < shelfBeans.size()) {
					codeList.add(shelfBeans.get(i));
					i++;
				} else {
					break;
				}
			}
			System.out.println(codeList);
			/*List<ShopCompleteAo> shopCompleteAoList = ShopRemoteUtils.queryByCodeList(codeList);

			result.addAll(shopCompleteAoList.stream().map(openShop -> {
				ShelfBean shelfBean = new ShelfBean();
				shelfBean.setBachShelfCode(openShop.getCode());
				shelfBean.setCityCode(openShop.getCityInfo().getCityCode());
				return shelfBean;
			}).collect(Collectors.toList()));*/
		}
	}

	private static List<Integer> generate(int i) {
		List<Integer> list = Lists.newArrayListWithExpectedSize(i);
		for (int j = 0; j < i; j++) {
			list.add(j);
		}
		return list;
	}

}
