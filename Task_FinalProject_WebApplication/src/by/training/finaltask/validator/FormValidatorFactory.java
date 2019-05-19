package by.training.finaltask.validator;

public class FormValidatorFactory {

    public FormValidatorFactory() {
    }

    public FormValidator getValidator(FormValidatorEnum validatorEnum)
    {
        if(validatorEnum != null)
        {
            switch(validatorEnum)
            {
                case USERFORM:
                    return new UserFormValidator();
                case USERINFOFORM:
                    return new UserInfoFormValidator();
                default:
                    break;
            }
        }
        throw new IllegalArgumentException("Invalid FormValidator Enum Argument!");
    }

}
