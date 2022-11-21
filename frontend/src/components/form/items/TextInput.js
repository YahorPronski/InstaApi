import '../../../assets/styles/components/form/items/textinput.scss';

const TextInput = ({label, name, value, onChange, hidden, required}) => {
    const type = hidden ? "password" : "text";
    const labelCss = required ? "required" : "";

    return (
        <div>
            <label className={labelCss}>{label}</label>
            <input
                className="text-input"
                name={name}
                value={value}
                type={type}
                onChange={onChange}/>
        </div>
    );
};

export default TextInput;