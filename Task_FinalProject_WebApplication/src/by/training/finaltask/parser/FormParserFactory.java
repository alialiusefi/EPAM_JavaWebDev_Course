package by.training.finaltask.parser;

public class FormParserFactory {

    public FormParserFactory() {
    }

    public FormParser getValidator(FormParserEnum validatorEnum)
    {
        if(validatorEnum != null)
        {
            switch(validatorEnum)
            {
                case USERFORM:
                    return new UserFormParser();
                case USERINFOFORM:
                    return new UserInfoFormParser();
                case PETFORM:
                    return new PetFormParser();
                default:
                    break;
            }
        }
        throw new IllegalArgumentException("Invalid FormParser Enum Argument!");
    }

}
