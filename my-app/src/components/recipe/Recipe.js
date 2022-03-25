import image from '../images/download.jpg'
import {Link} from "react-router-dom";
import '../UI/w3.css';
import './recipe.css';

const Recipe = (props) => {
    const linkTo = "/recipe-details/" + props.title + "/" + props.text;
    return (
        <Link to={linkTo} className="w3-quarter" style={{"padding": "8px"}}>
            <img src={image} alt="food" style={{"width": "100%"}}/>
            <h3>{props.title}</h3>
            <p className={"text"}>{props.text}</p>
        </Link>
    );
}
export default Recipe;