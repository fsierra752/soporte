package com.variada.utils.enums;

public enum EnumTablas {
  CABECERAS_CC(
      ".//div[contains(@id,'rowcheckcolumn') or contains(@class,'g-header-sort') or contains(@class,'x-column-header-first') or contains(@class,'x-column-header-inner x-column-header-inner-empty')or contains(@class,'x-column-header-inner')]//span"),
  REGISTROS_CC(
      ".//div[contains(@class,'x-grid-body')]//table/tbody/tr[contains(@class,'x-grid-data-row')]"),
  REGISTROS_PAGOS_CC(
      ".//div[contains(@class,'x-grid-body')]//table/tbody[contains(@id, 'gridview')]"),
  REGISTROS_CONTACTOS_CC(
      ".//div[contains(@class,'x-grid-body')]//table/tbody/tr[contains(@id, 'gridview')]");

  private String xpath;

  EnumTablas(String xpath) {
    this.xpath = xpath;
  }

  public String getXpath() {
    return xpath;
  }
}
