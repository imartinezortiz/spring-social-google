/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.google.api.gdata.impl;

import static org.springframework.util.StringUtils.hasText;
import static org.springframework.social.google.api.gdata.impl.Namespaces.AtomNamespace;
import static org.springframework.social.google.api.gdata.impl.Namespaces.GdataNamespace;
import nu.xom.Attribute;
import nu.xom.Element;

/**
 * A builder of XOM {@link Element}s for Atom entries.
 * @author Gabriel Axel
 */
public class ElementBuilder {

	public static ElementBuilder newAtomEntryBuilder() {
		return new ElementBuilder("entry", AtomNamespace);
	}
		
	public static ElementBuilder newGDataElementBuilder(String name) {
		return new ElementBuilder("gd:" + name, GdataNamespace);
	}
	
	private final Element element;
	
	public ElementBuilder(String name, String uri) {
		element = new Element(name, uri);
	}
	
	public Element getElement() {
		return element;
	}
	
	public ElementBuilder addElement(ElementBuilder child) {
		element.appendChild(child.getElement());
		return this;
	}
	
	public ElementBuilder addAttribute(String namespace, String name, String value) {
		element.addAttribute(new Attribute(name, namespace, value));
		return this;
	}
	
	public ElementBuilder addAttribute(String name, String value) {
		if(hasText(value)) {
			element.addAttribute(new Attribute(name, value));
		}
		return this;
	}
	
	public ElementBuilder addAttribute(String name, boolean value) {
		return addAttribute(name, Boolean.toString(value));
	}
	
	public ElementBuilder addElement(String namespace, String name, Enum<?> value) {
		if(value != null) {
			Element child = new Element(name, namespace);
			child.appendChild(value.name().toLowerCase());
			element.appendChild(child);
		}
		return this;
	}

	public ElementBuilder addSimpleAtomElement(String name, String value) {
		if(hasText(value)) {
			Element child = new Element(name, AtomNamespace);
			child.appendChild(value);
			element.appendChild(child);
		}
		return this;
	}
	
	public ElementBuilder addGDataElement(String name, String value) {
		if(hasText(value)) {
			Element child = new Element("gd:" + name, GdataNamespace);
			child.appendChild(value);
			element.appendChild(child);
		}
		return this;
	}

	public ElementBuilder setTitle(String title) {
		return addSimpleAtomElement("title", title);
	}
	
	public ElementBuilder setRel(String rel) {
		return addAttribute("rel", rel);
	}
	
	public ElementBuilder setHref(String href) {
		return addAttribute("href", href);
	}

	public ElementBuilder setValue(String value) {
		element.appendChild(value);
		return this;
	}
	
	public ElementBuilder setCategoryKind(String term) {
		Element category = new Element("category", AtomNamespace);
		category.addAttribute(new Attribute("scheme", "http://schemas.google.com/g/2005#kind"));
		category.addAttribute(new Attribute("term", term));
		element.appendChild(category);
		return this;
	}
}