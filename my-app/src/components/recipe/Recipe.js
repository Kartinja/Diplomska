import image from '../images/download.jpg'
import '../UI/w3.css';
import './recipes.css';

const Recipe = (props) => {
    return <div className="w3-quarter" style={{"padding":"8px"}}>
        <img src={image} alt="Sandwich" style={{"width":"100%"}}/>
        <h3>{props.title}</h3>
        <p style={{textAlign:'justify'}}>{props.text}</p>
    </div>
}
export default Recipe;