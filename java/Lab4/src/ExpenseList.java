public class ExpenseList
{
	private List<Expense> expenses;
	
	
	public ExpenseList()
	{
		expenses = new List<Expense>();
	}
	public void add(Expense exp)
	{
		expenses.add(exp);
	}
	
	public boolean isEmpty()
	{
		return expenses.isEmpty();
	}
	public boolean contains(Expense exp)
	{
		return expenses.contains(exp);
	}
	public Expense first()
	{
		return expenses.first();
	}
	public Expense next()
	{
		return expenses.next();
	}
	public void enumerate()
	{
		expenses.enumerate();
	}
	
	public double maxExpense()
	{
		double max =0.0, amt;
		Expense exp = expenses.first();
		while (exp!=null)
		{
			amt = exp.getAmount();
			if (amt>max)
				max = amt;
			exp = expenses.next();
		}
		return max;
	}
	public double minExpense()
	{
		double min =Double.MAX_VALUE, amt;
		Expense exp = expenses.first();
		if (exp==null) return 0.0;
		else
		{
		
			while (exp!=null)
			{
				amt = exp.getAmount();
				if (amt<min)
					min = amt;
				exp = expenses.next();
			}
		}
		return min;
	}
	public double avgExpense()
	{
		if (expenses.first() !=null)
        return totalExpense()/(double) expenses.size();
		else return 0.00;
	}
	public double totalExpense()
	{
		double total =0.00, amt;

		Expense exp = first();
		if (exp==null) return 0.0;
		else {

			while (exp!=null) {
				amt = exp.getAmount();
				total += amt;
				exp = next();
			}
		}

		return total;
	}

	public double amountSpentOn(String expItem)
	{
		double total =0.00, amt;

		Expense exp = first();
		if (exp==null) return 0.0;
		else {

			while (exp!= null) {
				amt = exp.getAmount();

				if (exp.getItem().equalsIgnoreCase(expItem))
				    total += amt;

				exp = next();
			}
		}


        return total;
	}
	
	
}
