package com.keepthinker.example.general.test.mokito;

public class Tank implements Attack{

	@Override
	public Result shot() {
		Result result = new Result();
		result.setSuccess(true);
		result.setMsg("Destroy a target");
		return result;
	}

	@Override
	public boolean ready() {
		return true;
	}

	@Override
	public Result shot(int times) {
		Result result = new Result();
		result.setSuccess(true);
		result.setMsg("Destroy " + times + " targets");
		return result;
	}

}
