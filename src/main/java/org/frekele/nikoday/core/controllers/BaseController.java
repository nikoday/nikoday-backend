package org.frekele.nikoday.core.controllers;

import org.frekele.nikoday.core.entities.PersistentBaseEntity;

import java.io.Serializable;

public interface BaseController<T extends PersistentBaseEntity, ID> extends Serializable {

}
