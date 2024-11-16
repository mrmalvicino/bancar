package utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import domainModel.Address;
import domainModel.Client;

public class Validator
{

	public static List<String> validateClientFields(Client client)
	{
		List<String> invalidFields = new ArrayList<>();

		// DNI
		if (client.getDni() == null || client.getDni().trim().isEmpty())
		{
			invalidFields.add("El DNI es requerido");
		} else if (!hasOnlyNumbers(client.getDni()))
		{
			invalidFields.add("El DNI solo puede contener números");
		}

		// CUIL
		if (client.getCuil() == null || client.getCuil().trim().isEmpty())
		{
			invalidFields.add("El CUIL es requerido");
		} else if (!hasOnlyNumbers(client.getCuil()))
		{
			invalidFields.add("El CUIL solo puede contener números");
		}

		// Teléfono
		if (client.getPhone() == null || client.getPhone().trim().isEmpty())
		{
			invalidFields.add("El Teléfono es requerido");
		} else if (!hasOnlyNumbers(client.getPhone()))
		{
			invalidFields.add("El Teléfono solo puede contener números");
		} else if (client.getPhone().length() != 10)
		{
			invalidFields.add("El Teléfono es inválido, debe contener 10 dígitos");
		}
		
		// Nombre
		if (client.getFirstName() == null
				|| client.getFirstName().trim().isEmpty())
		{
			invalidFields.add("El Nombre es requerido");
		} else if (!hasOnlyLetters(client.getFirstName()))
		{
			invalidFields.add("El Nombre solo puede contener letras");
		}

		// Apellido
		if (client.getLastName() == null
				|| client.getLastName().trim().isEmpty())
		{
			invalidFields.add("El Apellido es requerido");
		} else if (!hasOnlyLetters(client.getLastName()))
		{
			invalidFields.add("El Apellido solo puede contener letras");
		}

		// Email
		if (client.getEmail() == null || client.getEmail().trim().isEmpty())
		{
			invalidFields.add("El Email es requerido");
		} else if (!isValidEmail(client.getEmail()))
		{
			invalidFields.add("El formato del Email no es válido");
		}

		// Fecha de nacimiento
		if (client.getBirthDate() == null)
		{
			invalidFields.add("La Fecha de Nacimiento es requerida");
		} else if (!isAdult(client.getBirthDate()))
		{
			invalidFields.add("El cliente debe ser mayor de edad");
		}
		
		// Se suman los posibles campos invalidos de dirección
		invalidFields.addAll(validateAddressFields(client.getAddress()));
		
		return invalidFields;
	}

	public static List<String> validateAddressFields(Address address)
	{
		List<String> invalidFields = new ArrayList<String>();
		
		// TODO: validaciones de direcciones
		
		return invalidFields;
	}
	
	private static boolean hasOnlyNumbers(String str)
	{
		for (int i = 0; i < str.length(); i++)
		{
			if (!Character.isDigit(str.charAt(i)))
			{
				return false;
			}
		}
		return true;
	}

	private static boolean hasOnlyLetters(String str)
	{
		for (int i = 0; i < str.length(); i++)
		{
			if (!Character.isLetter(str.charAt(i)))
			{
				return false;
			}
		}
		return true;
	}
	
	private static boolean isAdult(Date birthDate)
	{
		
		LocalDate today = LocalDate.now();
		String birthStr = new SimpleDateFormat("yyyy-MM-dd").format(birthDate);
		LocalDate birthLocalDate = LocalDate.parse(birthStr);
		Period age = Period.between(birthLocalDate, today);
		return age.getYears() >= 18;
	}
	
	// Regexp tomada de https://stackoverflow.com/a/8204716
	private static boolean isValidEmail(String email)
	{
		Pattern emailRegex = 
			    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		return emailRegex.matcher(email).matches();
	}

}
