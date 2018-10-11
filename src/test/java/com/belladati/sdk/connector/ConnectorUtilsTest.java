package com.belladati.sdk.connector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * @author BellaDati Support
 */
public class ConnectorUtilsTest {

	@Test
	public void testGetSubtypesOf() {
		List<String> list = ConnectorUtils.getSubTypesOf(DataProviderApi.class);
		assertEquals(3, list.size());
		assertTrue(list.contains(TestDataProvider.class.getName()));
		assertTrue(list.contains(TestDataProviderFinal.class.getName()));
		assertTrue(list.contains(TestDataProviderStaticFinal.class.getName()));
	}

	public abstract class TestDataProviderAbstract extends DataProviderApi<RowsApi<? extends RowApi>> {

		protected TestDataProviderAbstract(Map<String, PropertyValueApi<?>> configuration) {
			super(configuration);
		}

	}

	public class TestDataProvider extends DataProviderApi<RowsApi<? extends RowApi>> {

		protected TestDataProvider(Map<String, PropertyValueApi<?>> configuration) {
			super(configuration);
		}

		@Override
		public Map<String, PropertyValueApi<?>> getDefaultProperties() {
			return null;
		}

		@Override
		public boolean check() throws Throwable {
			return false;
		}

		@Override
		public List<String> validate() {
			return null;
		}

		@Override
		public RowApi provideDefaultDataDefinition() {
			return null;
		}

		@Override
		public RowsApi<? extends RowApi> providePreviewData(int limit) {
			return null;
		}

		@Override
		public RowsApi<? extends RowApi> provideImportData(ProgressBarApi progressBar) {
			return null;
		}

	}

	public final class TestDataProviderFinal extends TestDataProvider {

		protected TestDataProviderFinal(Map<String, PropertyValueApi<?>> configuration) {
			super(configuration);
		}

	}

	public static final class TestDataProviderStaticFinal extends DataProviderApi<RowsApi<? extends RowApi>> {

		protected TestDataProviderStaticFinal(Map<String, PropertyValueApi<?>> configuration) {
			super(configuration);
		}

		@Override
		public Map<String, PropertyValueApi<?>> getDefaultProperties() {
			return null;
		}

		@Override
		public boolean check() throws Throwable {
			return false;
		}

		@Override
		public List<String> validate() {
			return null;
		}

		@Override
		public RowApi provideDefaultDataDefinition() {
			return null;
		}

		@Override
		public RowsApi<? extends RowApi> providePreviewData(int limit) {
			return null;
		}

		@Override
		public RowsApi<? extends RowApi> provideImportData(ProgressBarApi progressBar) {
			return null;
		}
	}

}
