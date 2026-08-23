package com.algaworks.algafood_api.api.exceptionHandle;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problem {

	private Integer status;
	private String type;
	private String title;
	private String detail;
	private String userMessage;
	private LocalDateTime timestamp;
	private List<Field> fields;
	
	@Getter
	@Builder
	public static class Field {
		private String name;
		private String userMessage;
	}
	
}
